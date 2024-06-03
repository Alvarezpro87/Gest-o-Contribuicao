// src/pages/AliquotaList.jsx
import { useState, useEffect } from "react";
import api from "../axiosconfig";
import "../Form/Form.css"; // Importação do CSS

const AliquotaList = () => {
  const [aliquotas, setAliquotas] = useState([]);

  useEffect(() => {
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

  return (
    <div className="form">
      <h1 className="h1">Lista de Alíquotas</h1>
      
      <ul>
        {aliquotas.map((aliquota) => (
          <li key={aliquota.id}>
            <p>{`Categoria: ${aliquota.categoria}, Salário Início: ${aliquota.salarioInicio}, Salário Fim: ${aliquota.salarioFim}, Valor Alíquota: ${aliquota.valorAliquota}`}</p>
         
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AliquotaList;
