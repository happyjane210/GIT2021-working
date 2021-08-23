const obj: any = {};
obj.name = "hong";
obj["phone"] = "1099293498";
delete obj.name;
console.log(obj);

// 배열
const arr: any[] = [];
arr.push({ name: "hong", phone: "8765" });
console.log(arr);

let var1: any;
var1 = "hong";
var1 = 123;
console.log(var1);
