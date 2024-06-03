import { useState, useEffect } from "react";
import api from "../axiosconfig";
import "../Form/Form.css";

const AliquotaEdit = () => {
  const [aliquotaDTO, setAliquotaDTO] = useState(null);
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const [aliquotas, setAliquotas] = useState([]);

  useEffect(() => {
    // Buscar todas as alíquotas disponíveis
    const fetchAliquotas = async () => {
      try {
        const response = await api.get("/aliquotas");
        setAliquotas(response.data);
      } catch (error) {
        console.error("Erro ao buscar alíquotas:", error);
      }
    };

    fetchAliquotas();
  }, []);

  const handleSearch = async (id) => {
    if (!id) {
      setMessage("Por favor, selecione uma alíquota.");
      return;
    }
    try {
      setLoading(true);
      setMessage("");
      const response = await api.get(`/aliquotas/${id}`);
      setAliquotaDTO(response.data);
    } catch (error) {
      console.error("Erro ao buscar alíquota:", error);
      setMessage("Erro ao buscar alíquota.");
    } finally {
      setLoading(false);
    }
  };

  const handleUpdate = async () => {
    if (!aliquotaDTO) {
      setMessage("Nenhuma alíquota selecionada para atualizar.");
      return;
    }
    try {
      setLoading(true);
      setMessage("");
      await api.put(`/aliquotas/${aliquotaDTO.id}`, aliquotaDTO);
      setMessage("Alíquota atualizada com sucesso.");
    } catch (error) {
      console.error("Erro ao atualizar alíquota:", error);
      setMessage("Erro ao atualizar alíquota.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="form">
      <h1 className="h1">Editar Alíquota</h1>
      <select
        onChange={(e) => handleSearch(e.target.value)}
        placeholder="Selecione a alíquota para editar"
      >
        <option value="">Selecione uma alíquota</option>
        {aliquotas.map((aliquota) => (
          <option key={aliquota.id} value={aliquota.id}>
            Categoria: {aliquota.categoria}, Salário Início: {aliquota.salarioInicio}, Salário Fim: {aliquota.salarioFim}
          </option>
        ))}
      </select>
      {loading && (
        <div className="br-loading medium" role="progressbar" aria-label="carregando exemplo medium exemplo"></div>
      )}
      {message && <p>{message}</p>}
      {aliquotaDTO && (
        <div>
          <input
            type="text"
            value={aliquotaDTO.salarioInicio}
            onChange={(e) => setAliquotaDTO({ ...aliquotaDTO, salarioInicio: e.target.value })}
            placeholder="Salário Início"
          />
          <input
            type="text"
            value={aliquotaDTO.salarioFim}
            onChange={(e) => setAliquotaDTO({ ...aliquotaDTO, salarioFim: e.target.value })}
            placeholder="Salário Fim"
          />
          <input
            type="text"
            value={aliquotaDTO.valorAliquota}
            onChange={(e) => setAliquotaDTO({ ...aliquotaDTO, valorAliquota: e.target.value })}
            placeholder="Valor Alíquota"
          />
          <button onClick={handleUpdate} disabled={loading}>
            {loading ? "Atualizando..." : "Atualizar"}
          </button>
        </div>
      )}
    </div>
  );
};

export default AliquotaEdit;
