# LinkeTinder - LinkedIn meets Tinder

Uma plataforma revolucionária que combina a funcionalidade profissional do LinkedIn com a interface inovadora do Tinder para conectar candidatos e empresas de forma anônima e gamificada.

## 🎯 Visão do Produto

**LinkeTinder** é uma aplicação web que revoluciona o processo de recrutamento através de:

- 💼 **Ambiente profissional** similar ao LinkedIn (vagas, networking, carreiras)
- 💕 **Sistema de matching anônimo** inspirado no Tinder (swipe, privacidade, gamificação)
- 🎨 **Visual moderno e intuitivo** com micro-interações sofisticadas
- 🤖 **IA integrada** para compatibilidade e recomendações personalizadas

## 🚀 Funcionalidades Principais

### ✅ Implementadas (MVP)
- [x] **Sistema de Autenticação** - Login/registro com toggle candidato/empresa
- [x] **Interface de Discovery** - Swipe cards para matching entre vagas e candidatos
- [x] **Design System Moderno** - Material Design customizado com glass morphism
- [x] **Navegação Responsiva** - Header adaptativo e bottom navigation mobile
- [x] **Arquitetura Escalável** - Lazy loading, standalone components, NgRx ready

### 🔄 Em Desenvolvimento
- [ ] **Sistema de Mensagens** - Chat pós-match com templates
- [ ] **Dashboard Analytics** - Métricas e insights para empresas
- [ ] **Perfis Completos** - Gestão de perfis profissionais
- [ ] **Algoritmo de IA** - Matching baseado em compatibilidade
- [ ] **Gamificação** - Sistema de conquistas e pontuação

### 🎯 Roadmap Futuro
- [ ] **PWA Completo** - Notificações push e modo offline
- [ ] **Mobile App** - Aplicativo nativo React Native
- [ ] **Integração LinkedIn** - Import de dados profissionais
- [ ] **Video Entrevistas** - Sistema integrado de entrevistas
- [ ] **Analytics Avançado** - Machine learning para insights

## 🏗️ Arquitetura Técnica

### Stack Principal
- **Framework**: Angular 19 com Standalone Components
- **UI Library**: Angular Material 19 com design customizado
- **Estado**: NgRx 18 para gerenciamento de estado
- **Styling**: SCSS com Design System moderno
- **Animações**: Angular Animations + CSS personalizado
- **PWA**: Angular Service Worker (em desenvolvimento)

### Estrutura do Projeto
```
src/app/
├── core/                   # Singleton services (auth, api, state, guards)
├── shared/                 # Componentes reutilizáveis (UI, animations, pipes)
├── features/              # Módulos de funcionalidade
│   ├── auth/              # Sistema de autenticação
│   ├── discovery/         # Interface principal de swipe
│   ├── dashboard/         # Painéis e analytics
│   ├── profile/           # Gestão de perfis
│   ├── messaging/         # Sistema de chat
│   └── settings/          # Configurações do usuário
└── styles/                # Design system e temas
```

## 🎨 Design System

### Paleta de Cores
- **Primary Blue**: #3B82F6 (Profissional LinkedIn)
- **Primary Orange**: #F97316 (Energia Tinder)
- **Gradient**: Combinação harmoniosa dos primários
- **Semantic**: Verde (sucesso), Amarelo (warning), Vermelho (erro)

### Componentes UI
- **Glass Morphism**: Cards translúcidos com backdrop-filter
- **Micro-interações**: Hover effects e animações suaves
- **Responsive Design**: Mobile-first com breakpoints adaptáveis
- **Typography**: Inter + Poppins para máxima legibilidade

## 🚦 Como Executar

### Pré-requisitos
- Node.js 18+ 
- npm 9+
- Angular CLI 19

### Instalação
```bash
# Clone o repositório
git clone https://github.com/guiaf04/LinkeTinder.git
cd LinkeTinder/angular-frontend

# Instale as dependências
npm install

# Execute em modo desenvolvimento
npm start

# Acesse http://localhost:4200
```

### Comandos Disponíveis
```bash
npm start          # Servidor de desenvolvimento
npm run build      # Build de produção
npm test           # Executar testes
npm run lint       # Verificar código
npm run e2e        # Testes end-to-end
```

## 📱 Demonstração

### Funcionalidades Atuais
1. **Login Screen**: Interface moderna com toggle candidato/empresa
2. **Discovery Interface**: Cards de swipe com informações profissionais
3. **Navigation**: Header responsivo e bottom nav mobile
4. **Loading States**: Animações e feedback visual
5. **Notifications**: Sistema de notificações em tempo real

### Próximas Implementações
- Dashboard com métricas e gráficos
- Sistema completo de messaging
- Perfis detalhados com portfolio
- Algoritmo de matching inteligente

## 🔧 Desenvolvimento

### Tecnologias Utilizadas
- **Angular 19**: Framework principal
- **Material Design**: Componentes UI
- **SCSS**: Preprocessador CSS
- **TypeScript**: Linguagem principal
- **RxJS**: Programação reativa
- **NgRx**: Gerenciamento de estado

### Padrões Seguidos
- **Standalone Components**: Arquitetura moderna do Angular
- **Lazy Loading**: Carregamento otimizado de rotas
- **Feature Modules**: Organização modular do código
- **Design Patterns**: Service pattern, Observer, Facade

## 📊 Performance

### Métricas Atuais
- **Bundle Size**: ~628KB (inicial)
- **Lazy Loading**: Componentes carregados sob demanda
- **Tree Shaking**: Código não utilizado removido
- **Minificação**: Assets otimizados para produção

### Otimizações Implementadas
- Standalone components para menor bundle
- Lazy loading de rotas e componentes
- OnPush change detection strategy
- CSS otimizado com design tokens

## 🤝 Contribuição

### Como Contribuir
1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

### Diretrizes
- Siga os padrões de código estabelecidos
- Adicione testes para novas funcionalidades
- Mantenha a documentação atualizada
- Use commits semânticos

## 📄 Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 👥 Equipe

- **Desenvolvimento**: Guilherme AF
- **Design**: Sistema próprio baseado em Material Design
- **Arquitetura**: Angular 19 + NgRx + Material

## 📞 Contato

- **GitHub**: [@guiaf04](https://github.com/guiaf04)
- **Projeto**: [LinkeTinder](https://github.com/guiaf04/LinkeTinder)

---

**LinkeTinder** - Conectando talentos e oportunidades de forma inovadora! 🚀
