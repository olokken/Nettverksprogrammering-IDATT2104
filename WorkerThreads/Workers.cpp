#include <thread>
#include <mutex>
#include <condition_variable> 
#include <functional>
#include <iostream>
#include <list>
#include <vector> 
#include <iostream> 

using namespace std;

class Workers {
    
        int num_threads; 
        list<thread> workers; 
        list<function<void()>> tasks; 
        condition_variable cv; 
        mutex tasks_mutex; 
        mutex wait_mutex; 
        bool wait = true; 
        bool stop = false; 
        mutex stop_mutex;


    public:
        Workers(int num) {
            num_threads = num; 
        }    

        void post_timeout(function<void()> task, int timeout) {
            unique_lock<mutex> lock(tasks_mutex); 
            tasks.emplace_back([task, timeout] {
                this_thread::sleep_for(chrono::milliseconds(timeout));
                task();
            }); 
            cv.notify_one(); 
        }

        void start() {
            tasks.clear();
            for (int i = 0; i < num_threads; i++) {
                workers.emplace_back([i, this] {
                    bool done = false;
                    bool tasks_left = false; 
 
                    while(!done) {
                        function<void()> task; 

                        if(!tasks_left) {
                            unique_lock<mutex> lock(wait_mutex);
                            while (wait) {
                                cv.wait(lock); 
                            }
                        }
                        {
                            unique_lock<mutex> lock(tasks_mutex); 

                            if(!tasks.empty()) {
                                task = *tasks.begin();
                                tasks.pop_front(); 
                                tasks_left = true; 
                            } else {
                                {
                                    unique_lock<mutex> lock (stop_mutex); 
                                    if (stop) {
                                        done = true; 
                                    } else {
                                        unique_lock<mutex> lock(wait_mutex);
                                        tasks_left = false; 
                                        wait = true; 
                                    }
                                }
                            }
                        }
                        if(task) {
                            task(); 
                        }
                    }
                })
            ;} 
        }

        void post(function<void()> task) {
            {
                lock_guard<mutex> lock(tasks_mutex);
                tasks.emplace_back(task);
            }

            {
                lock_guard<mutex> lock(wait_mutex);
                wait = false;
            }
            cv.notify_all();
        }

    void stopping(){
        {
            unique_lock<mutex> lock(stop_mutex);
            stop = true;
        }
        {
            lock_guard<mutex> lock(wait_mutex);
            wait = false;
        }
        cv.notify_all();
    }

    void join() {
        stopping(); 
        for (auto &thread: workers){
            thread.join();
        }
    }
}; 


