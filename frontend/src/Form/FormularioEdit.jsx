// src/Components/Formulario/AliquotaEdit.jsx
import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import './Form.css';

const AliquotaEdit = () => {
  const { id } = useParams();
  const [categoria, setCategoria] = useState("");
  const [salarioInicio, setSalarioInicio] = useState("");
  const [salarioFim, setSalarioFim] = useState("");
  const [valorAliquota, setValorAliquota] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchAliquota = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/aliquotas/${id}`);
        const { categoria, salarioInicio, salarioFim, valorAliquota } = response.data;
        setCategoria(categoria);
        setSalarioInicio(salarioInicio);
        setSalarioFim(salarioFim);
        setValorAliquota(valorAliquota);
      } catch (error) {
        console.error("Erro ao buscar alíquota:", error);
      }
    };

    fetchAliquota();
  }, [id]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await axios.put(`http://localhost:8080/aliquotas/${id}`, {
        categoria,
        salarioInicio,
        salarioFim,
        valorAliquota,
      });
      navigate("/aliquotas");
    } catch (error) {
      console.error("Erro ao atualizar alíquota:", error);
    }
  };

  return (
    <div className="form">
      <h1 className="h1">Editar Alíquota</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Categoria"
          value={categoria}
          onChange={(e) => setCategoria(e.target.value)}
        />
        <input
          type="number"
          placeholder="Salário Início"
          value={salarioInicio}
          onChange={(e) => setSalarioInicio(e.target.value)}
        />
        <input
          type="number"
          placeholder="Salário Fim"
          value={salarioFim}
          onChange={(e) => setSalarioFim(e.target.value)}
        />
        <input
          type="number"
          placeholder="Valor Alíquota"
          value={valorAliquota}
          onChange={(e) => setValorAliquota(e.target.value)}
        />
        <button type="submit">Atualizar</button>
      </form>
    </div>
  );
};

export default AliquotaEdit;
