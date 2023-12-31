# -*- coding: utf-8 -*-
"""Cópia de Olá, este é o Colaboratory

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1Y-Xzj_0oF2szsf0ZiUaYZcAC5YOBFe0D

Importando bibliotecas e lendo os arquivos
"""

import os
import pandas as pd

lista_arquivo = os.listdir("/content/drive/MyDrive/Vendas")
display(lista_arquivo)

"""Criando um DataFrame"""

tabela_total = pd.DataFrame()

for arquivo in lista_arquivo:
  if "Vendas" in arquivo:
    tabela = pd.read_csv(f"/content/drive/MyDrive/Vendas/{arquivo}")
    tabela_total = tabela_total.append(tabela)

display(tabela_total)

"""Calcular o produto mais vendido (Quantidade) e mostrar do maior para o menor"""

tabela_produtos = tabela_total.groupby('Produto').sum()
tabela_produtos = tabela_produtos[["Quantidade Vendida"]].sort_values(by="Quantidade Vendida", ascending=True)
display(tabela_produtos)

"""Calculando o produto que mais faturou. Criar uma coluna 'Faturamento' para mostrar."""

tabela_total['Faturamento'] = tabela_total['Quantidade Vendida'] * tabela_total['Preco Unitario']

tabela_faturamento = tabela_total.groupby('Produto').sum()
tabela_faturamento = tabela_faturamento[['Faturamento']].sort_values(by='Faturamento', ascending=False)
display(tabela_faturamento)


"""Calculando qual loja mais faturou e mostrando em um grafico"""


tabela_lojas = tabela_total.groupby('Loja').sum()
tabela_lojas = tabela_lojas[['Faturamento']]
display(tabela_lojas)

import plotly.express as px

grafico = px.bar(tabela_lojas, x=tabela_lojas.index, y='Faturamento')
grafico.show()