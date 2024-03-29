package ru.s1uad_dw;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    private Dictionary currentDict;
    private final ArrayList<Dictionary> allDictionaries;

    public App() {
        allDictionaries = new ArrayList<>();
    }

    public void start() {
        System.out.println("Доброе пожаловать в консольное приложение, которое позволяет работать со словорями различных языков.");
        while (selectAction()) {
            acceptContinue();
        }

    }

    private boolean selectAction() {
        System.out.println("""
                Выберите действие из представленных ниже:
                1 - Просмотр словарей
                2 - Создание словаря
                3 - Выбор словаря
                4 - Просмотр содержимого выбранного словаря
                5 - Добавление слова в выбранный словарь
                6 - Удаление слова из выбранного словаря
                7 - Найти перевод слова в выбранном словаре
                8 - Экспортировать словарь в файл json
                9 - Импортировать словарь из файла json
                10 - Выход
                """);
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        switch (action) {
            case "1":
                viewAllDictionaries();
                break;
            case "2":
                addDictionary();
                break;
            case "3":
                selectCurrentDict();
                break;
            case "4":
                viewCurrentDictWords();
                break;
            case "5":
                addWordInCurrentDict();
                break;
            case "6":
                removeWordFromCurrentDict();
                break;
            case "7":
                findWordInCurrentDict();
                break;
            case "8":
                exportCurrentDictToJson();
                break;
            case "9":
                importDictFromJson();
                break;
            case "10":
                return false;
            default:
                System.out.println("Пожалуйста, выберите цифру, соответствующую действию которое вы хотите выполнить:");
        }
        return true;
    }

    private void acceptContinue() {
        System.out.println("Нажмите Enter, чтоб вернуться к выбору действий...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.print("\033[H\033[J");
        System.out.flush();
    }

    private void viewAllDictionaries() {
        if (!allDictionaries.isEmpty()) {
            System.out.println("Словари (выбранный помечен звездочкой):");
            for (Dictionary dict : allDictionaries) {
                System.out.printf("Словарь %s %1s\n", dict.getTitle(), dict.equals(currentDict) ? "*" : "");
            }
        } else {
            System.out.println("На данный момент в приложение не добавлен ни один словарь.");
        }

    }

    private void addDictionary() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название словаря:");
        String title = scanner.nextLine();

        System.out.println("Введите первый язык словаря:");
        String language1 = scanner.nextLine();

        System.out.println("Введите второй язык словаря:");
        String language2 = scanner.nextLine();

        System.out.println("Хотите установить ограничения для словаря? (y для подтверждения)");
        String limitationAnswer = scanner.nextLine();

        Dictionary newDict = new Dictionary(title, language1, language2);

        if (limitationAnswer.equalsIgnoreCase("y")) {
            System.out.printf("Настройка ограничений для языка \"%s\":\n", language1);
            System.out.println("Введите максимальную длину слова (-1 если нет ограничения по длине):");
            int maxSize1 = Integer.parseInt(scanner.nextLine());

            System.out.println("Можно ли использовать цифры в словах? y/n");
            boolean allowedDigits1 = scanner.nextLine().equalsIgnoreCase("y");

            System.out.println("Можно ли использовать буквы в словах? y/n");
            boolean allowedLetters1 = scanner.nextLine().equalsIgnoreCase("y");

            System.out.println("Можно ли использовать пробелы в словах? y/n");
            boolean allowedSpaces1 = scanner.nextLine().equalsIgnoreCase("y");

            System.out.println("Можно ли использовать символы в словах? y/n");
            boolean allowedSymbols1 = scanner.nextLine().equalsIgnoreCase("y");


            System.out.printf("Настройка ограничений для языка \"%s\":\n", language2);
            System.out.println("Введите максимальную длину слова (-1 если нет ограничения по длине):");
            int maxSize2 = Integer.parseInt(scanner.nextLine());

            System.out.println("Можно ли использовать цифры в словах? y/n");
            boolean allowedDigits2 = scanner.nextLine().equalsIgnoreCase("y");

            System.out.println("Можно ли использовать буквы в словах? y/n");
            boolean allowedLetters2 = scanner.nextLine().equalsIgnoreCase("y");

            System.out.println("Можно ли использовать пробелы в словах? y/n");
            boolean allowedSpaces2 = scanner.nextLine().equalsIgnoreCase("y");

            System.out.println("Можно ли использовать символы в словах? y/n");
            boolean allowedSymbols2 = scanner.nextLine().equalsIgnoreCase("y");

            if (maxSize1 == -1) {
                newDict.setLimitationLanguage1(new Limitation(allowedDigits1,
                        allowedLetters1, allowedSpaces1, allowedSymbols1));
            } else {
                newDict.setLimitationLanguage1(new Limitation(allowedDigits1,
                        allowedLetters1, allowedSpaces1, allowedSymbols1, maxSize1));
            }
            if (maxSize2 == -1) {
                newDict.setLimitationLanguage2(new Limitation(allowedDigits2,
                        allowedLetters2, allowedSpaces2, allowedSymbols2));
            } else {
                newDict.setLimitationLanguage2(new Limitation(allowedDigits2,
                        allowedLetters2, allowedSpaces2, allowedSymbols2, maxSize2));
            }
            allDictionaries.add(newDict);
            System.out.println("Словарь " + title + " создан!");
            return;
        }
        allDictionaries.add(newDict);
        System.out.println("Словарь " + title + " создан!");
    }

    private void selectCurrentDict() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название словаря, который хотите выбрать:");
        String title = scanner.nextLine();

        for (Dictionary dict : allDictionaries) {
            if (dict.getTitle().equals(title)) {
                currentDict = dict;
                System.out.println("Словарь " + title + " выбран! Теперь действия 4-9 будут выполняться для этого словаря.");
                return;
            }
        }
        System.out.println("Словарь " + title + " не найден в списке словарей!");
    }

    private void viewCurrentDictWords() {
        if (currentDict != null && !currentDict.getWords().isEmpty()) {
            System.out.println("Содержимое словаря " + currentDict.getTitle() + ":");
            System.out.println(currentDict);
        } else if (currentDict != null) {
            System.out.println("Словарь " + currentDict.getTitle() + " пуст.");
        } else {
            System.out.println("Не удалось вывести содержимое словаря. Словарь не выбран.");
        }
    }

    private void addWordInCurrentDict() {
        if (currentDict != null) {
            Scanner scanner = new Scanner(System.in);
            String answer;
            do {
                System.out.println("Введите слово на языке " + currentDict.getLanguage1() + ":");
                String wordLang1 = scanner.nextLine();

                System.out.println("Введите слово на языке " + currentDict.getLanguage2() + ":");
                String wordLang2 = scanner.nextLine();

                if (currentDict.getLimitationLanguage1().check(wordLang1) &&
                        currentDict.getLimitationLanguage2().check(wordLang2)) {
                    currentDict.addWord(wordLang1, wordLang2);
                    System.out.println("Слово успешно добавлено. Хотите добавить еще одно слово? y/n");
                    answer = scanner.nextLine();
                } else {
                    System.out.println("Не удалось добавить слово. Слово не соответствует ограничениям языков словаря.");
                    System.out.printf("Ограничения для языка %s\n", currentDict.getLanguage1());
                    System.out.println(currentDict.getLimitationLanguage1());
                    System.out.printf("Ограничения для языка %s\n", currentDict.getLanguage2());
                    System.out.println(currentDict.getLimitationLanguage2());
                    return;
                }
            } while (answer.equals("y"));
        } else {
            System.out.println("Не удалось начтать операцию добавления слова. Словарь не выбран.");
        }
    }

    private void removeWordFromCurrentDict() {
        if (currentDict != null) {
            Scanner scanner = new Scanner(System.in);
            String answer;
            do {
                System.out.println("Введите слово, которое хотите удалить на любом из языков словаря");
                String wordToRemove = scanner.nextLine();

                try {
                    currentDict.removeWord(wordToRemove);
                    System.out.println("Слово успешно удалено. Хотите удалить еще одно слово? y/n");
                    answer = scanner.nextLine();
                } catch (NoSuchElementException e) {
                    System.out.println("Введенное вами слово не найдено в словаре.");
                    System.out.println("Хотите удалить другое слово? y/n");
                    answer = scanner.nextLine();
                }
            } while (answer.equals("y"));
        } else {
            System.out.println("Не удалось начтать операцию удаления слова. Словарь не выбран.");
        }
    }

    private void findWordInCurrentDict() {
        if (currentDict != null) {
            Scanner scanner = new Scanner(System.in);
            String answer;
            do {
                System.out.println("Введите слово, которое хотите найти на любом из языков словаря");
                String wordToFind = scanner.nextLine();

                try {
                    System.out.println(currentDict.getWord(wordToFind));
                    System.out.println("Хотите найти еще одно слово? y/n");
                    answer = scanner.nextLine();
                } catch (NoSuchElementException e) {
                    System.out.println("Введенное вами слово не найдено в словаре.");
                    System.out.println("Хотите найти другое слово? y/n");
                    answer = scanner.nextLine();
                }
            } while (answer.equals("y"));
        } else {
            System.out.println("Не удалось начтать операцию поиска слова. Словарь не выбран.");
        }
    }

    private void exportCurrentDictToJson() {
        if (currentDict != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите полный путь к дирректории, в коротую хотите экспортировать словарь:");
            String filepath = scanner.nextLine();
            System.out.println("Введите название файла, в который хотите экспортировать словарь:");
            filepath += File.separator + scanner.nextLine() + ".json";

            try {
                currentDict.saveToFile(filepath);
                System.out.println("Словарь успешно экспортирован в файл json. Вы можете найти его по пути:\n" + filepath);
            } catch (IOException e) {
                System.out.println("Ошибка во время экспорта словаря в файл json:\n" + e.getMessage());
            }
        } else {
            System.out.println("Не удалось начтать операцию экспорта словаря. Словарь не выбран.");
        }
    }

    private void importDictFromJson() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите полный путь к файлу, из которого хотите импортировать словарь:");
        String filepath = scanner.nextLine();
        try {
            Dictionary importedDict = Dictionary.loadFromFile(filepath);
            allDictionaries.add(importedDict);
            System.out.println("Словарь " + importedDict.getTitle() + " успешно импортирован");
        } catch (IOException e) {
            System.out.println("Ошибка во время импорта словаря:\n" + e.getMessage());
        }
    }
}
