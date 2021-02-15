use std::io; 
use std::thread;

fn main() {
    let mut start = String::new();
    let mut slutt = String::new();
    let mut threads = Vec::new();
    let primtall: Vec<i32> = Vec::new();
     
    println!("Skriv det tallet du vil intervallet skal starte");
    io::stdin().read_line(&mut start).expect("Klarte ikke lese linjen"); 

    println!("Skriv det tallet du vil intervallet skal slutte");
    io::stdin().read_line(&mut slutt).expect("Klarte ikke lese linjen");  

    let start_tall:u32 = start.trim().parse().unwrap();
    let slutt_tall:u32 = slutt.trim().parse().unwrap(); 
    
    

    for i in 0..4 {
        threads.push(thread::spawn(move || {
            // i is copied into thread
            println!("Output from thread {}", i);
        }));
    }

    for thread in threads {
        let _ = thread.join(); // let _ means that the return value should be ignored
    }

}

fn is_prime(num: u32) -> bool {
    if num>1 {
      for i in 2..num/2 {
            if num % i == 0 {
                return false;
            }
        }
        return true;
        }
    else {
        return false;
    }
} 

fn finn_intervaller(start:u32, slutt:u32, antall:u32) -> [i32] {
    
}




