use std::io; 
use std::thread;
use std::sync::{Arc, Mutex}; 

fn main() {
    let antall_traader = 7; 
    let mut start = String::new();
    let mut slutt = String::new();
    let mut threads = Vec::new();
    let primtall = Arc::new(Mutex::new(vec![])); 

     
    println!("Skriv det tallet du vil intervallet skal starte");
    io::stdin().read_line(&mut start).expect("Klarte ikke lese linjen"); 

    println!("Skriv det tallet du vil intervallet skal slutte");
    io::stdin().read_line(&mut slutt).expect("Klarte ikke lese linjen");  

    let start_tall:u32 = start.trim().parse().unwrap();
    let slutt_tall:u32 = slutt.trim().parse().unwrap(); 
    let intervaller = Arc::new(Mutex::new(finn_intervaller(start_tall, slutt_tall, antall_traader))); 
    
    for i in 0..antall_traader {
        let clone = Arc::clone(&primtall); 
        let clone2 = Arc::clone(&intervaller); 
        let h = thread::spawn(move || {
            let mut data = clone.lock().unwrap();
            let data2 = clone2.lock().unwrap();
            for j in data2[i as usize]..data2[(i+1) as usize] {
                if is_prime(j) {
                    data.push(j); 
                }
            }
        });
        threads.push(h); 
    }

    for thread in threads {
        let _ = thread.join(); // let _ means that the return value should be ignored
    }
    primtall.lock().unwrap().sort(); 
    println!("{:?}", primtall); 

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

fn finn_intervaller(start:u32, slutt:u32, antall:u32) -> Vec<u32> {
    let mut vec = Vec::new();
    vec.push(start); 
    let storrelse = (slutt - start)/antall;
    let mut neste = start + storrelse; 
    for _i in 0..antall-1 { 
        vec.push(neste);
        neste += storrelse;  
    }
    vec.push(slutt); 
    return vec; 
}




