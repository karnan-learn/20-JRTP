function greet(msg:string, ...names:string[]) : string {
	
	return msg + " " + names.join(",") ;
}

console.log(greet('Hi', 'Ashok' , 'IT')); //OK
console.log(greet('Hello')); //Ok

function add (a:number, b:number) : number;

function add (a:string, b:string) : string;

function add (a:any, b:any) : any {
	return a + b;
}

add("10","20");
add("Ashok", "IT");