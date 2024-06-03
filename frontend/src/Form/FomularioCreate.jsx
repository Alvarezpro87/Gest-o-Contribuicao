// src/pages/AliquotaCreate.jsx
import { useState } from "react";
import api from "../axiosconfig";
import "../Form/Form.css"; // Importação do CSS

const AliquotaCreate = () => {
  const [categoria, setCategoria] = useState("");
  const [salarioInicio, setSalarioInicio] = useState("");
  const [salarioFim, setSalarioFim] = useState("");
  const [valorAliquota, setValorAliquota] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = {
      categoria,
      salarioInicio: salarioInicio.replace(",", ""),
      salarioFim: salarioFim.replace(",", ""),
      valorAliquota: parseFloat(valorAliquota.replace(",", ".")).toFixed(1),
    };

    try {
      const response = await api.post("/aliquotas", data);
      console.log("Alíquota criada com sucesso:", response.data);
      setCategoria("");
      setSalarioInicio("");
      setSalarioFim("");
      setValorAliquota("");
      setMessage("Alíquota criada com sucesso");
    } catch (error) {
      console.error("Erro ao criar alíquota:", error);
      setMessage("Categoria Duplicada");
    }
  };

  return (
    <div className="form">
      <h1 className="h1">Criar Alíquota</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Categoria"
          value={categoria}
          onChange={(e) => setCategoria(e.target.value)}
        />
        <input
          type="text"
          placeholder="Salário Início"
          value={salarioInicio}
          onChange={(e) => setSalarioInicio(e.target.value)}
        />
        <input
          type="text"
          placeholder="Salário Fim"
          value={salarioFim}
          onChange={(e) => setSalarioFim(e.target.value)}
        />
        <input
          type="text"
          placeholder="Valor Alíquota"
          value={valorAliquota}
          onChange={(e) => setValorAliquota(e.target.value)}
        />
        <button type="submit">Criar</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default AliquotaCreate;
