package com.company;

import java.util.*;
import java.util.function.Consumer;

public class Main {
    static final int NUMBER_OF_EXECUTIONS = 100;
    static final String JACK = "Jack";
    private static String temp;

    public static void main(String[] args) {
        var linkedList = new LinkedList<String>();
        var arrayList = new ArrayList<String>();
        var arrayDeque = new ArrayDeque<String>();
        var stack = new Stack<String>();

        String[] names = {
                "Александр",
                "Анна",
                "Матвей",
                "Роман",
                "Екатерина",
                "Максим",
                "Виктория",
                "Татьяна",
                "Михаил",
                "Андрей",
                "Ирина",
                "София",
                "Артем",
                "Ева",
                "Тимур",
                "Алиса",
                "Лев",
                "Константин",
                "Анастасия",
                "Ксения"};

        int[] arrNumber = new int[]{100, 2000, 5000};
        for (int i = 0; i < arrNumber.length; i++) {
            String[] arrNames = fillArray(names, arrNumber[i]);
            int index = (int) (Math.random() * arrNames.length);

            // Таблица результатов
            System.out.printf("Количество %10s | LinkedList | ArrayList | ArrayDeque |   Stack |",  + arrNumber[i]);
            System.out.println();
            System.out.printf("Вставка               | %10s | %9s | %10s | %7s |",
                    calc(linkedList, (array) -> fillCollection(array, arrNames)),
                    calc(arrayList, (array) -> fillCollection(array, arrNames)),
                    calc(arrayDeque, (array) -> fillCollection(array, arrNames)),
                    calc(stack, (array) -> fillCollection(array, arrNames)));
            System.out.println();

            System.out.printf("Замена элемента  %4s | %10s | %9s | %10s | %7s |",
                    index,
                    calc(linkedList, (array) -> replaceRandomElement(array, index, JACK)),
                    calc(arrayList, (array) -> replaceRandomElement(array, index, JACK)),
                    calc(arrayDeque, (array) -> replaceRandomElement(array, index, JACK)),
                    calc(stack, (array) -> replaceRandomElement(array, index, JACK)));
            System.out.println();

            System.out.printf("Поиск элемента        | %10s | %9s | %10s | %7s |",
                    calc(linkedList, (array) -> findElement(array, JACK)),
                    calc(arrayList, (array) -> findElement(array, JACK)),
                    calc(arrayDeque, (array) -> findElement(array, JACK)),
                    calc(stack, (array) -> findElement(array, JACK)));
            System.out.println();

            System.out.printf("Удаление элемента     | %10s | %9s | %10s | %7s |",
                    calc(linkedList, (array) -> removeElement(array, JACK)),
                    calc(arrayList, (array) -> removeElement(array, JACK)),
                    calc(arrayDeque, (array) -> removeElement(array, JACK)),
                    calc(stack, (array) -> removeElement(array, JACK)));
            System.out.println();

            System.out.printf("Доступ к первым 5%%    | %10s | %9s | %10s | %7s |",
                    calc(linkedList, Main::getFirstFivePercentElements),
                    calc(arrayList, Main::getFirstFivePercentElements),
                    calc(arrayDeque, Main::getFirstFivePercentElements),
                    calc(stack, Main::getFirstFivePercentElements));
            System.out.println();

            System.out.printf("Доступ к последним 5%% | %10s | %9s | %10s | %7s |",
                    calc(linkedList, Main::getLastFivePercentElements),
                    calc(arrayList, Main::getLastFivePercentElements),
                    calc(arrayDeque, Main::getLastFivePercentElements),
                    calc(stack, Main::getLastFivePercentElements));
            System.out.println();

            System.out.printf("Удаление первых 5%%    | %10s | %9s | %10s | %7s |",
                    calc(linkedList, Main::removeFirstFivePercentElements),
                    calc(arrayList, Main::removeFirstFivePercentElements),
                    calc(arrayDeque, Main::removeFirstFivePercentElements),
                    calc(stack, Main::removeFirstFivePercentElements));
            System.out.println();

            System.out.printf("Удаление последних 5%% | %10s | %9s | %10s | %7s |",
                    calc(linkedList, Main::removeLastFivePercentElements),
                    calc(arrayList, Main::removeLastFivePercentElements),
                    calc(arrayDeque, Main::removeLastFivePercentElements),
                    calc(stack, Main::removeLastFivePercentElements));
            System.out.println("\n");
        }

    }

    // Метод заполнения массива элементами вспомогательного массива
    public static String[] fillArray(String[] arr, int length) {
        String[] arrNames = new String[length];
        for (int i = 0; i < arrNames.length; i++) {
            arrNames[i] = arr[(int) (Math.random() * arr.length)];
        }
        return arrNames;
    }

    // Метод заполнения коллекции элементами массива
    public static void fillCollection(Collection<String> collection, String[] arr) {
        for (String str : arr) {
            collection.add(str);
        }
    }

    // Методы замены случайного элемента
    public static void replaceRandomElement(List<String> list, int index, String str) {
        list.set(index, str);
    }

    public static void replaceRandomElement(ArrayDeque<String> arrayDeque, int index, String str) {
        var tempArrayList = new ArrayList<String>();
        if (index < arrayDeque.size() / 2) {
            while (index >= 0) {
                tempArrayList.add(arrayDeque.removeFirst());
                index--;
            }
            arrayDeque.addFirst(str);
            for (int i = tempArrayList.size() - 2; i >= 0; i--) {
                arrayDeque.addFirst(tempArrayList.get(i));
            }
        } else {
            int numberElements = arrayDeque.size() - index;
            while (numberElements > 0) {
                tempArrayList.add(arrayDeque.removeLast());
                numberElements--;
            }
            arrayDeque.addLast(str);
            for (int i = tempArrayList.size() - 2; i >= 0; i--) {
                arrayDeque.addLast(tempArrayList.get(i));
            }
        }
    }

    public static void replaceRandomElement(Stack<String> stack, int index, String str) {
        var tempArrayList = new ArrayList<String>();
        int numberElements = stack.size() - index;
        while (numberElements > 0) {
            tempArrayList.add(stack.pop());
            numberElements--;
        }
        stack.push(str);
        for (int i = tempArrayList.size() - 2; i >= 0; i--) {
            stack.push(tempArrayList.get(i));
        }
    }

    private static void getElement(String str) {
        temp = str;
    }

    // Метод поиска элемента
    public static void findElement(Collection<String> collection, String str) {
        if (collection.contains(str)) {
            getElement(str);
        }
    }

    // Методы удаления элемента
    public static void removeElement(List<String> list, String str) {
        list.remove(str);
    }

    public static void removeElement(ArrayDeque<String> arrayDeque, String str) {
        var tempArrayList = new ArrayList<String>();
        while (!arrayDeque.getFirst().equals(str)) {
            tempArrayList.add(arrayDeque.removeFirst());
        }
        arrayDeque.removeFirst();
        for (int i = tempArrayList.size() - 1; i >= 0; i--) {
            arrayDeque.addFirst(tempArrayList.get(i));
        }
    }

    public static void removeElement(Stack<String> stack, String str) {
        var tempArrayList = new ArrayList<String>();
        while (!stack.peek().equals(str)) {
            tempArrayList.add(stack.pop());
        }
        stack.pop();
        for (int i = tempArrayList.size() - 1; i >= 0; i--) {
            stack.push(tempArrayList.get(i));
        }
    }

    // Метод получения доступа к первым 5% значений (по одному, без вывода)
    public static void getFirstFivePercentElements(Collection<String> collection) {
        int amountOfElements = (int) (collection.size() * 0.05);
        var i = collection.iterator();
        while (i.hasNext() && amountOfElements > 0) {
            getElement(i.next());
            amountOfElements--;
        }
    }

    // Методы получения доступа к последним 5% значений (по одному, без вывода)
    public static void getLastFivePercentElements(Collection<String> collection) {
        int amountOfElements = (int) (collection.size() * 0.95);
        var i = collection.iterator();
        while (i.hasNext() && amountOfElements > 0) {
            i.next();
            amountOfElements--;
        }
        while (i.hasNext()) {
            getElement(i.next());
        }
    }

    public static void getLastFivePercentElements(LinkedList<String> linkedList) {
        int amountOfElements = (int) (linkedList.size() * 0.05);
        var i = linkedList.descendingIterator();
        while (amountOfElements > 0) {
            getElement(i.next());
            amountOfElements--;
        }
    }

    public static void getLastFivePercentElements(ArrayList<String> arrayList) {
        int startElement = (int) (arrayList.size() * 0.95);
        for (int i = startElement; i < arrayList.size(); i++) {
          getElement(arrayList.get(i));
        }
    }

    public static void getLastFivePercentElements(ArrayDeque<String> arrayDeque) {
        int amountOfElements = (int) (arrayDeque.size() * 0.05);
        var i = arrayDeque.descendingIterator();
        while (amountOfElements > 0) {
            getElement(i.next());
            amountOfElements--;
        }
    }

    // Методы удаления первых 5% значений (по одному)
    public static void removeFirstFivePercentElements(Collection<String> collection) {
        int amountOfElements = (int) (collection.size() * 0.05);
        var i = collection.iterator();
        while (i.hasNext() && amountOfElements > 0) {
            i.next();
            i.remove();
            amountOfElements--;
        }
    }

    public static void removeFirstFivePercentElements(Stack<String> stack) {
        int amountOfElements = (int) (stack.size() * 0.95);
        var tempArrayList = new ArrayList<String>();
        while (amountOfElements > 0) {
            tempArrayList.add(stack.pop());
            amountOfElements--;
        }
        while (!stack.isEmpty()) {
            stack.pop();
        }
        for (int i = tempArrayList.size() - 1; i >= 0; i--) {
            stack.push(tempArrayList.get(i));
        }
    }

    // Методы удаления последних 5% значений (по одному)
    public static void removeLastFivePercentElements(LinkedList<String> linkedList) {
        int amountOfElements = (int) (linkedList.size() * 0.05);
        while (amountOfElements > 0) {
            linkedList.removeLast();
            amountOfElements--;
        }
    }

    public static void removeLastFivePercentElements(ArrayList<String> arrayList) {
        int amountOfElements =  (int) (arrayList.size() * 0.05);
        while (amountOfElements > 0) {
            arrayList.remove(arrayList.size()  - 1);
            amountOfElements--;
        }
    }

    public static void removeLastFivePercentElements(Deque<String> arrayDeque) {
        int amountOfElements = (int) (arrayDeque.size() * 0.05);
        while (amountOfElements > 0) {
            arrayDeque.removeLast();
            amountOfElements--;
        }
    }

    public static void removeLastFivePercentElements(Stack<String> stack) {
        int amountOfElements = (int) (stack.size() * 0.05);
        while (amountOfElements > 0) {
            stack.pop();
            amountOfElements--;
        }
    }

    // Метод подсчёта времени
    public static <E> long calc(E data, Consumer<E> func) {
        var time = 0;
        for (var i = 1; i <= NUMBER_OF_EXECUTIONS; i++) {
            var newData = clone(data);
            long start = System.nanoTime();
            func.accept(newData);
            time += (System.nanoTime() - start);
        }
        func.accept(data);
        return time / NUMBER_OF_EXECUTIONS / 1000;
    }

    // Метод клонирования коллекции
    private static <E> E clone(E data) {
        if (data instanceof LinkedList<?>) {
            return (E) ((LinkedList<?>) data).clone();
        }
        if (data instanceof ArrayList<?>) {
            return (E) ((ArrayList<?>) data).clone();
        }
        if (data instanceof ArrayDeque<?>) {
            return (E) ((ArrayDeque<?>) data).clone();
        }
        if (data instanceof Stack<?>) {
            return (E) ((Stack<?>) data).clone();
        }
        return null;
    }
}
