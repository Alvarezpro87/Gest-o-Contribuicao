import '../Form/Form.css'; // Reutilizando o CSS existente

const HomeContribuicao = () => {
  return (
    <div className="home-container">
      <h1 className="home-title">Gestão Contribuição</h1>
      <p className="home-description">
        Bem-vindo ao sistema de Gestão de Contribuintes. Aqui você pode gerenciar todas as alíquotas e consultar contribuintes.
      </p>
      <div className="home-features">
        <div className="feature-item">
          <h2>Gerenciamento de Alíquotas</h2>
          <p>Liste, Adicione, edite e remova alíquotas de forma simples e rápida.</p>
        </div>
        <div className="feature-item">
          <h2>Consulta de Contribuintes</h2>
          <p>Consulte informações detalhadas sobre os contribuintes cadastrados.</p>
        </div>
        
      </div>
    </div>
  );
};

export default HomeContribuicao;
