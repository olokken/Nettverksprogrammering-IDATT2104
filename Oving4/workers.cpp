using namespace std;
# include <functional>
# include <iostream>
# include <list>
# include <mutex>
# include <thread>
# include <vector>
# include <condition_variable> 

class workers {
    public: 
        vector<thread> worker_threads;
        int num_of_threads;

        workers (int num_threads) {
            num_of_threads = num_threads; 
        }
        


        
}; 