import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../axiosconfig";
import "../Form/Form.css";

const AliquotaDelete = () => {
  const [categoria, setCategoria] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const [categorias, setCategorias] = useState([]); 
  const navigate = useNavigate();

  useEffect(() => {
    // Função para buscar as categorias disponíveis
    const fetchCategorias = async () => {
      try {
        const response = await api.get("/aliquotas");
        const categoriasUnicas = [...new Set(response.data.map(aliquota => aliquota.categoria))];
        setCategorias(categoriasUnicas);
      } catch (error) {
        console.error("Erro ao buscar categorias:", error);
      }
    };

    fetchCategorias();
  }, []);

  const handleDelete = async () => {
    if (!categoria) {
      setMessage("Por favor, selecione uma categoria.");
      return;
    }

    try {
      setLoading(true);
      await api.delete(`/aliquotas/categoria/${categoria}`);
      setMessage("Alíquota deletada com sucesso.");
      setTimeout(() => {
        navigate("/aliquotas");
      }, 2000); // Redireciona após 2 segundos
    } catch (error) {
      console.error("Erro ao deletar alíquotas:", error);
      setMessage("Erro ao deletar a alíquota. Categoria não encontrada.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="form">
      <h1 className="h1">Deletar Alíquota</h1>
      <select
        value={categoria}
        onChange={(e) => setCategoria(e.target.value)}
        placeholder="Selecione a categoria da alíquota"
      >
        <option value="">Selecione uma categoria</option>
        {categorias.map((cat, index) => (
          <option key={index} value={cat}>
            {cat}
          </option>
        ))}
      </select>
      <button onClick={handleDelete} disabled={loading}>
        {loading ? "Deletando..." : "Confirmar"}
      </button>
      {loading && (
        <div className="br-loading medium" role="progressbar" aria-label="carregando exemplo medium exemplo"></div>
      )}
      {message && <p>{message}</p>}
    </div>
  );
};

export default AliquotaDelete;
