import { faker } from "@faker-js/faker";

faker.setLocale("ja");

console.log("INSERT INTO purchase.item (name, price) VALUES");
const set = new Set();
for (let i = 0; i < 1000; i++) {
  const word = `'${faker.lorem.word()}'`;
  set.add(word);
}
for (let i = 0; i < 100000; i++) {
  const word = `'うまい棒${i}'`;
  set.add(word);
}
Array.from(set).forEach((word, index) => {
  const price = faker.commerce.price();
  const data = [word, price];
  const separator = index === set.size - 1 ? ";" : ",";
  console.log(`(${data.join(",")})${separator}`);
});
