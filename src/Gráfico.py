import matplotlib.pyplot as plt
#vontade de gerar os gráficos de cada um aqui com execução e tudo
# Dados para o gráfico
x = [0,10000, 100000, 500000, 1000000]
y1 = [0,629766700, 6061287600, 70722064600, 240386591100]
y2 = [0,95353700, 615684200, 2517541100,4231684800 ]
y3 =[0,82480600,573515700,2612424100,4604279300]
# Criar o gráfico de linhas
plt.plot(x, y1, label='Quick Sort')
plt.plot(x, y2, label='Merge Sort')
plt.plot(x, y3, label ='Heap Sort')
# Adicionar rótulos aos eixos
plt.xticks(range(1,1000000000))
plt.yticks(range(1,1000000000)) 
plt.xlabel('Tamanho do arquivo')
plt.ylabel('Tempo gasto em nanossegundos')

# Adicionar título ao gráfico
plt.title('Gráfico com Duas Linhas')

# Adicionar legenda
plt.legend()

# Exibir o gráfico
plt.show()

