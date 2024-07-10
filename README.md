# Консольное приложение, реализующее функционал формирования чека в магазине
Для запуска данного приложения требуется ввести в консоль команду вида:
<br>***java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java id-quantity discountCard=xxxx balanceDebitCard=xxxx pathToFile=XXXX saveToFile=xxxx*** <br>где:
- *id* - идентификатор товара
- *quantity* - количество товара
- *discountCard=xxxx* - название и номер дисконтной карты
- *balanceDebitCard=xxxx* - баланс на дебетовой карте
- *pathToFile=xxxx* - путь до CSV-файла(включая название файла с расширением) с данными о товаре
- *saveToFile=xxxx* - путь до CSV-файла(включая название файла с расширением) куда сохраняем результат

<br>**Пример консольной команды**:
<br>***java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100 pathToFile=./products.csv saveToFile=./error_result.csv***<br>
После ввода команды сформируется CSV-файл который будет распологаться по пути saveToFile, содержащий в себе список товаров и их количество с ценой, а также рассчитанную сумму с учетом скидки по предъявленной карте, если она есть.
 
