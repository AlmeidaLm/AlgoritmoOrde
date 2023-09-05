import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class Gptquick {
    public static class SortNames extends JFrame {
        private JProgressBar progressBar;

        public SortNames() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Ordenação de Nomes");
            setSize(300, 100);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            progressBar = new JProgressBar(0, 100);
            panel.add(progressBar);

            add(panel);
        }

        public void startSorting() {
            Thread thread = new Thread(() -> {
                String s;
                int v = 0;
                while (true) {
                    int lineCount = 0;
                    System.out.println("Digite o arquivo a ser ordenado \n");
                    System.out.println("1.Arquivo de 10k\n2.Arquivo de 100k\n3.Arquivo de 500k\n4.Arquivo de 1m.\n5.Sair");
                    Scanner p = new Scanner(System.in);
                    MenuZin n = new MenuZin();
                    int opc = p.nextInt();
                    s = n.menu(opc);
                    if (opc == 5) {
                        System.exit(0);
                    }
                    try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
                        while (reader.readLine() != null) {
                            lineCount++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String[] lines;
                    if(v>0) {
                        lines = null;
                        System.gc();
                    }else {
                        lines = new String[lineCount];
                        v++;
                    }
                    if(lines == null){
                        lines = new String[lineCount];
                    }

                    try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
                        String line;
                        int index = 0;
                        while ((line = reader.readLine()) != null) {
                            lines[index] = line;
                            index++;
                        }
                        long inicio = System.nanoTime();
                        quickSort(lines, 0, lines.length - 1);
                        long fim = System.nanoTime();
                        System.out.println("O processo demorou "+ (fim - inicio )+ " Nanossegundos \n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
        }

        private void quickSort(String[] arr, int low, int high) {
            if (low < high) {
                int pi = partition(arr, low, high);

                quickSort(arr, low, pi - 1);
                quickSort(arr, pi + 1, high);
            }
        }

        private int partition(String[] arr, int low, int high) {
            String pivot = arr[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (arr[j].compareTo(pivot) < 0) {
                    i++;
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }

                int progress = (int) (((double) (j - low) / (high - low - 1)) * 100);
                SwingUtilities.invokeLater(() -> {
                    progressBar.setValue(progress);
                });
            }

            String temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;

            return i + 1;
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                SortNames sortNames = new SortNames();
                sortNames.setVisible(true);
                sortNames.startSorting();
            });
        }
    }
}
