import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class GptInsert {
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
                int i = 0;
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
                    if(i>0) {
                        lines = null;
                        System.gc();
                    }else {
                        lines = new String[lineCount];
                        i++;
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
                        insertionSort(lines);
                        long fim = System.nanoTime();
                        System.out.println("O processo demorou "+ (fim - inicio )+ " Nanossegundos \n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
        }

        private void insertionSort(String[] arr) {
            int n = arr.length;
            for (int i = 1; i < n; i++) {
                String key = arr[i];
                int j = i - 1;
                while (j >= 0 && arr[j].compareTo(key) > 0) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = key;

                int progress = (int) (((double) i / (n - 1)) * 100);
                SwingUtilities.invokeLater(() -> {
                    progressBar.setValue(progress);
                });
            }
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

