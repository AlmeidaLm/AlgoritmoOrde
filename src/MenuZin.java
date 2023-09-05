public class MenuZin {
    String menu  (int a){// especificar o caminho do arquivo na execução.
        String s;
       if(a == 1){
           s = "C:\\Users\\koke2\\OneDrive\\Documentos\\Estrutura de Dados\\Materialpordenar\\nomes10k.txt";
           return s;
        }
       if(a == 2){
           s = "C:\\Users\\koke2\\OneDrive\\Documentos\\Estrutura de Dados\\Materialpordenar\\nomes100k.txt";
           return s;
       }
       if(a == 3){
           s = "C:\\Users\\koke2\\OneDrive\\Documentos\\Estrutura de Dados\\Materialpordenar\\nomes500k.txt";
           return s;
       }
       if (a == 4){
           s = "C:\\Users\\koke2\\OneDrive\\Documentos\\Estrutura de Dados\\Materialpordenar\\nomes1m.txt";
           return s;
       }
      return null;
   }
}
