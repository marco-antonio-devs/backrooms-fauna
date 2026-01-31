# Backrooms: Fauna

O jogo-eletrônico **Backrooms: Fauna** é um projeto sobre *biologia especulativa* utilizando uma *base científica rígida*.
O ambiente trata-se de uma *reinterpretação* do conceito de **Backrooms** como um complexo de origem humana.

# História

O projeto inicializou-se em *2023* com o nome **Spatial**, sem foco na biologia das criaturas.
O uso da **ITsMagic Engine** (explicada abaixo) foi substituida em 2024 e posteriormente retornado por ser significativamente mais ergonômico (pessoalmente) em relação à linguagem **Haxe** com a biblioteca **Lime** e **OpenFL**.
Atualmente, o projeto encontra-se engavetado, embora seja altamente provável um retorno futuro.

# Sobre os códigos

Neste repositório, você irá encontrar vários códigos escritos na linguagem de programação principal da denominada **ITsMagic Engine** (Java 7.0 por razões a serem explicadas abaixo).

# Compatibilidade

O uso de uma tecnologia obsoleta (Java 7.0) tem uma base séria relacionada ao compilador.
- Após analisar os arquivos de meta-dados localizados para cada, foi descoberto de que tal compilador seja baseado no uso de **Dalvik** (usando o arquivo `.dex` gerado a partir dos arquivos de código-fonte).
- Basicamente, um compilador antigo e o único escolhido pelo criador da **ITsMagic Engine**, que não podê utilizar do **Gradle** como ferramenta integrada.
- Segundo o Lucas (criador da **ITsMagic Engine**), compiladores modernos requerem do **Gradle**, que por sua vez é *praticamente* impossível de integrar funcionalmente no ambiente **Android**.
