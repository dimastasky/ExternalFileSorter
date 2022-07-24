# ExternalFileSorter

## Постановка задачи
Напишите построчную сортировку большого текстового файла, не влезающего в оперативную память.
Размер требуемой памяти не должен зависеть от размера файла.

Длина строки разумная, одна строка сильно меньше чем объем памяти.

Для проверки работоспособности нужен генератор таких файлов, принимающий в качестве параметров количество строк и их максимальную длину.

### Пример работы сортировки файла
Строки в файле до сортировки:
```
bcddd36352a
abcdd33562f
fgyyyz378
fghhhh456
```

После сортировки:
```
abcdd33562f
bcddd36352a
fghhhh456
fgyyyz378
```

## Как запустить программу:
1. С помощью команды Maven package создать исполняемый jar-файл;
2. Перейти в папку target, где находится jar-файл;
3. Выполнить java -jar CustisTestTask-1.0-SNAPSHOT-jar-with-dependencies.jar;
3.1. Также можно выделить определенное количество памяти с помощью -Xmx512M, например
      java -Xmx512M -jar CustisTestTask-1.0-SNAPSHOT-jar-with-dependencies.jar;
