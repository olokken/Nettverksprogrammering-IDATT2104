#include "Workers.cpp"
using namespace std; 

int main() {
    Workers workers(4); 
    Workers event_loop(1); 
    workers.start(); 
    event_loop.start(); 

    workers.post_timeout([] {
       cout<< "Hei fra post workout : " << this_thread::get_id();  
    }, 5000);
    workers.post([] {
        cout << "Hei fra den første posten i worker threadene som kjører i tråd: " << this_thread::get_id() << endl; 
    });

    workers.post([] {
        cout << "Hei fra den andre posten i worker threadene, som kjører i tråd: " << this_thread::get_id() << endl; 
    });

    event_loop.post([] {
        cout << "Hei fra den første posten i event-loopen, som kjører i tråd: " << this_thread::get_id() << endl; 
    });

    event_loop.post([] {
        cout << "Hei fra den andre posten i event-loopen, som kjører i tråd: " << this_thread::get_id() << endl; 
    });

    workers.join(); 
    event_loop.join(); 

    return 0; 
}

