import java.util.Arrays;

public class SortingComparison {

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000};

        for (int size : sizes) {
            int[] arr = generateRandomArray(size);

            // Bubble Sort
            int[] arrBubble = arr.clone();
            long startTimeBubble = System.nanoTime();
            int swapsBubble = bubbleSort(arrBubble);
            long endTimeBubble = System.nanoTime();
            long timeBubble = endTimeBubble - startTimeBubble;

            // Insertion Sort
            int[] arrInsertion = arr.clone();
            long startTimeInsertion = System.nanoTime();
            int swapsInsertion = insertionSort(arrInsertion);
            long endTimeInsertion = System.nanoTime();
            long timeInsertion = endTimeInsertion - startTimeInsertion;

            // Selection Sort
            int[] arrSelection = arr.clone();
            long startTimeSelection = System.nanoTime();
            int swapsSelection = selectionSort(arrSelection);
            long endTimeSelection = System.nanoTime();
            long timeSelection = endTimeSelection - startTimeSelection;

            // Shaker Sort
            int[] arrShaker = arr.clone();
            long startTimeShaker = System.nanoTime();
            int swapsShaker = shakerSort(arrShaker);
            long endTimeShaker = System.nanoTime();
            long timeShaker = endTimeShaker - startTimeShaker;

            // Gnome Sort
            int[] arrGnome = arr.clone();
            long startTimeGnome = System.nanoTime();
            int swapsGnome = gnomeSort(arrGnome);
            long endTimeGnome = System.nanoTime();
            long timeGnome = endTimeGnome - startTimeGnome;

            // Quick Sort
            int[] arrQuick = arr.clone();
            long startTimeQuick = System.nanoTime();
            quickSort(arrQuick, 0, arrQuick.length - 1);
            long endTimeQuick = System.nanoTime();
            long timeQuick = endTimeQuick - startTimeQuick;

            // Radix Sort
            int[] arrRadix = arr.clone();
            long startTimeRadix = System.nanoTime();
            radixSort(arrRadix);
            long endTimeRadix = System.nanoTime();
            long timeRadix = endTimeRadix - startTimeRadix;

            System.out.println("Results for array size " + size + ":");
            System.out.println("Bubble Sort - Swaps: " + swapsBubble + ", Time: " + timeBubble + " ns");
            System.out.println("Insertion Sort - Swaps: " + swapsInsertion + ", Time: " + timeInsertion + " ns");
            System.out.println("Selection Sort - Swaps: " + swapsSelection + ", Time: " + timeSelection + " ns");
            System.out.println("Shaker Sort - Swaps: " + swapsShaker + ", Time: " + timeShaker + " ns");
            System.out.println("Gnome Sort - Swaps: " + swapsGnome + ", Time: " + timeGnome + " ns");
            System.out.println("Quick Sort - Time: " + timeQuick + " ns");
            System.out.println("Radix Sort - Time: " + timeRadix + " ns");
            System.out.println();
        }
    }

    // Методы сортировки и вспомогательные методы

    // Генерация случайного массива
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        return arr;
    }

    // Bubble Sort
    public static int bubbleSort(int[] arr) {
        int n = arr.length;
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
        }
        return swaps;
    }

    // Insertion Sort
    public static int insertionSort(int[] arr) {
        int n = arr.length;
        int swaps = 0;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                swaps++;
            }
            arr[j + 1] = key;
        }
        return swaps;
    }

    // Selection Sort
    public static int selectionSort(int[] arr) {
        int n = arr.length;
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            swaps++;
        }
        return swaps;
    }

    // Shaker Sort
    public static int shakerSort(int[] arr) {
        int n = arr.length;
        int swaps = 0;
        boolean swapped;
        for (int i = 0; i < n / 2; i++) {
            swapped = false;
            for (int j = i; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    swaps++;
                }
            }
            for (int j = n - 2 - i; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return swaps;
    }

    // Gnome Sort
    public static int gnomeSort(int[] arr) {
        int n = arr.length;
        int swaps = 0;
        int index = 0;
        while (index < n) {
            if (index == 0) {
                index++;
            }
            if (arr[index] >= arr[index - 1]) {
                index++;
            } else {
                int temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index--;
                swaps++;
            }
        }
        return swaps;
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Radix Sort
    public static void radixSort(int[] arr) {
        int max = getMax(arr);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
}