// src/pages/ContribuinteConsulta.jsx
import { useState } from "react";
import api from "../axiosconfig";
import "../Form/Form.css";

const ContribuinteConsulta = () => {
  const [cpf, setCpf] = useState("");
  const [contribuinte, setContribuinte] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSearch = async () => {
    if (!cpf) {
      setError("Por favor, insira um CPF.");
      return;
    }

    try {
      setLoading(true);
      setError(null);
      const response = await api.get(`/contribuintes/consultar/${cpf}`);
      setContribuinte(response.data);
    } catch (error) {
      setError("Erro ao buscar contribuinte");
      console.error("Erro ao buscar contribuinte:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="form">
      <h1 className="h1">Consulta de Contribuinte</h1>
      <input
        type="text"
        value={cpf}
        onChange={(e) => setCpf(e.target.value)}
        placeholder="Digite o CPF do contribuinte"
      />
      <button onClick={handleSearch} disabled={loading}>
        {loading ? "Consultando..." : "Consultar"}
      </button>
      {error && <p className="error">{error}</p>}
      {contribuinte && (
        <div>
          <p>CPF: {contribuinte.cpf}</p>
          <p>Categoria: {contribuinte.categoria}</p>
          <p>Salário: {contribuinte.salario}</p>
          <p>Alíquota: {contribuinte.aliquota}%</p>
          <p>Tempo de Contribuição: {contribuinte.tempoContribuicaoMeses} meses</p>
          <p>Valor Contribuição Mensal: {contribuinte.valorContribuicaoMensal}</p>
          <p>Total Contribuído (Sem Ajuste): {contribuinte.totalContribuidoSemAjuste}</p>
          <p>Valor Ajuste Aplicado: {contribuinte.valorAjusteAplicado}</p>
          <p>Total Contribuído (Ajustado): {contribuinte.totalContribuidoAjustado}</p>
        </div>
      )}
    </div>
  );
};

export default ContribuinteConsulta;
