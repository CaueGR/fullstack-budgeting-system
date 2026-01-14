## 🏗️ Arquitetura do Sistema.
Abaixo, o diagrama de classes planejado para o backend do sistema.

```mermaid
classDiagram
    %% PACOTE MODEL 
    class Usuario {
        -Long id
        -String username
        -String password
        +getId()
        +getUsername()
        +getPassword()
    }

    class Material {
        -Long id
        -String nome
        -Double precoUnitario
        +getUsuario()
    }

    class Orcamento {
        -Long id
        -String nomeCliente
        -LocalDateTime dataCriacao
        -BigDecimal multiplicador
        +getTotalMateriais() BigDecimal
        +getValorFinal() BigDecimal
        +adicionarItem(item)
    }

    class OrcamentoItem {
        -Long id
        -Integer quantidade
        -BigDecimal precoNoMomento
        +getSubtotal() BigDecimal
    }

    %% PACOTE REPOSITORY (Interfaces)
    class UsuarioRepository {
        <<Interface>>
        +findByUsername(String)
    }
    class MaterialRepository {
        <<Interface>>
    }
    class OrcamentoRepository {
        <<Interface>>
    }

    %% PACOTE SERVICE 
    class UsuarioService {
        +salvar(Usuario)
        +buscarPorUsername(String)
    }
    class MaterialService {
        +salvar(Material)
        +listarTodos()
    }
    class TokenService {
        -String secret
        +gerarToken(Usuario)
        +validateToken(String)
    }

    %% PACOTE CONTROLLER 
    class AuthController {
        +login(LoginDTO)
        +registrar(Usuario)
    }
    class MaterialController {
        +criar(Material)
        +listar()
    }
    
    %% PACOTE SECURITY 
    class SecurityFilter {
        +doFilterInternal()
    }

    %% RELACIONAMENTOS
    
    %% Usuario tem Varios Materiais
    Usuario "1" --> "*" Material : possui
    
    %% Usuario cria Varios Orcamentos
    Usuario "1" --> "*" Orcamento : cria
    
    %% Orcamento é composto por Varios Itens 
    Orcamento "1" *-- "*" OrcamentoItem : contem
    
    OrcamentoItem "*" --> "1" Material : refere-se a

    %% Dependencias de Camadas 
    AuthController ..> UsuarioService : usa
    AuthController ..> TokenService : usa
    MaterialController ..> MaterialService : usa
    
    UsuarioService ..> UsuarioRepository : usa
    MaterialService ..> MaterialRepository : usa
    
    SecurityFilter ..> TokenService : valida token
