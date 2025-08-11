# Guia de Linting - LinkeTinder

## Checkstyle

Este projeto utiliza Checkstyle para manter a consistência e qualidade do código Java.

### Regras Configuradas

- **Nomenclatura**: Convenções Java padrão para classes, métodos, variáveis, etc.
- **Importações**: Evita imports com wildcard (*), remove imports não utilizados
- **Espaçamento**: Regras de indentação (4 espaços), espaçamento ao redor de operadores
- **Tamanho**: Métodos limitados a 50 linhas, máximo 7 parâmetros por método
- **Linhas**: Máximo 120 caracteres por linha
- **Blocos**: Uso obrigatório de chaves, posicionamento correto
- **Boas Práticas**: Evita statements vazios, implementa equals/hashCode consistentemente

### Comandos Disponíveis

```bash
# Executar checkstyle apenas no código principal
./gradlew checkstyleMain

# Executar checkstyle apenas nos testes
./gradlew checkstyleTest

# Executar checkstyle em todos os arquivos Java
./gradlew checkstyleAll

# Executar todas as verificações (incluindo testes)
./gradlew check
```

### Relatórios

Os relatórios são gerados em:
- **HTML**: `build/reports/checkstyle/main.html`
- **XML**: `build/reports/checkstyle/main.xml`

### Supressões

Algumas regras são suprimidas em contextos específicos:
- Números mágicos em testes
- Design para extensão em DTOs
- Visibilidade em entidades JPA
- Parâmetros finais em controladores

### Configuração

- **Arquivo principal**: `config/checkstyle/checkstyle.xml`
- **Supressões**: `config/checkstyle/checkstyle-suppressions.xml`

### IDE Integration

Para integrar com sua IDE:

#### IntelliJ IDEA
1. Instale o plugin "CheckStyle-IDEA"
2. Configure para usar: `config/checkstyle/checkstyle.xml`

#### VS Code
1. Instale a extensão "Checkstyle for Java"
2. Configure o caminho para o arquivo de configuração
