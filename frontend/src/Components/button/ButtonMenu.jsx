import "./Button.css";
import { Link } from "react-router-dom"; 

const ButtonMenu = () => {
  return (
    <div className="menu-panel">
      <div className="col-lg-12 mb-5">
        <div className="br-menu push active">
          <div className="menu-container position-static">
            <div className="menu-panel h-auto position-static shadow-lg-right">
              <nav className="menu-body">
                <div className="menu-folder">
                  <Link className="menu-item" to="#">
                    <span className="icon">
                      <i className="fas fa-book" aria-hidden="true"></i>
                    </span>
                    <span className="content">Alíquotas</span>
                  </Link>
                  <ul>
                    <li>
                      <Link className="menu-item" to="/aliquotas">
                        <span className="icon">
                          <i className="fas fa-list" aria-hidden="true"></i>
                        </span>
                        <span className="content">Listar</span>
                      </Link>
                    </li>
                    <li>
                      <Link className="menu-item" to="/aliquotas/criar">
                        <span className="icon">
                          <i className="fas fa-plus" aria-hidden="true"></i>
                        </span>
                        <span className="content">Criar</span>
                      </Link>
                    </li>
                    <li>
                      <Link className="menu-item" to="/aliquotas/editar">
                        <span className="icon">
                          <i className="fas fa-edit" aria-hidden="true"></i>
                        </span>
                        <span className="content">Editar</span>
                      </Link>
                    </li>
                    <li>
                      <Link className="menu-item" to="/aliquotas/deletar">
                        <span className="icon">
                          <i className="fas fa-trash" aria-hidden="true"></i>
                        </span>
                        <span className="content">Deletar</span>
                      </Link>
                    </li>
                  </ul>
                </div>
                <div className="menu-folder">
                  <Link className="menu-item" to="#">
                    <span className="icon">
                      <i className="fas fa-bell" aria-hidden="true"></i>
                    </span>
                    <span className="content">Contribuição</span>
                  </Link>
                  <ul>
                    <li>
                      <Link className="menu-item" to="/contribuintes/consultar">
                        <span className="icon">
                          <i className="fas fa-calculator" aria-hidden="true"></i>
                        </span>
                        <span className="content">Calculo da Contribuição</span>
                      </Link>
                    </li>
                  </ul>
                </div>
              </nav>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ButtonMenu;
