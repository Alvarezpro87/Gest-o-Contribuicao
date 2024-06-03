import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter as Router, Routes, Route, Outlet } from "react-router-dom";
import Header from "./pages/Header";
import Footer from "./pages/Footer";
import AliquotaList from "./pages/AliquotaList";
import AliquotaCreate from "./pages/AliquotaCreate";
import AliquotaEdit from "./pages/AliquotaEdit";
import AliquotaDelete from "./pages/AliquotaDelete";
import ContribuinteConsulta from "./pages/ContribuinteConsulta";
import HomeContribuicao from "./pages/HomeContribuicao";
import NotFoundPage from "./pages/NotFoundPage";


const Layout = () => (
  <div>
    <Header />
    <Outlet />
    <Footer />
  </div>
);

const App = () => {
  return (
    <Router>
      <Routes>      
        <Route path="/" element={<Layout />}>
          <Route index element={<HomeContribuicao />} /> 
          <Route path="aliquotas" element={<AliquotaList />} />
          <Route path="aliquotas/criar" element={<AliquotaCreate />} />
          <Route path="aliquotas/editar" element={<AliquotaEdit />} />
          <Route path="aliquotas/deletar" element={<AliquotaDelete />} />
          <Route path="contribuintes/consultar" element={<ContribuinteConsulta />} />
          <Route path="*" element={<NotFoundPage />} />
        </Route>
      </Routes>
    </Router>
  );
};

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
