import { useState } from "react";
import Govlogo from "../assets/images/govbr.svg";
import ButtonMenu from "../Components/button/ButtonMenu";
import { Link } from "react-router-dom";
import "./Header.css"; 

const Header = () => {
  const [isMenuVisible, setIsMenuVisible] = useState(false);

  const toggleMenu = () => {
    setIsMenuVisible(!isMenuVisible);
  };

  return (
    <header className="br-header small">
      <div className="container-lg">
        <div className="header-top">
          <Link to="/" className="header-logo" style={{ cursor: 'pointer' }}>
            <img src={Govlogo} alt="Logo do GovBR" />
          </Link>
          <div className="header-actions">
            <div className="header-links dropdown">
              <button
                className="br-button circle small"
                type="button"
                data-toggle="dropdown"
                aria-label="Abrir Acesso Rápido"
              >
                <i className="fas fa-ellipsis-v" aria-hidden="true"></i>
              </button>
              <div className="br-list">
                <div className="header">
                  <div className="title">Acesso Rápido</div>
                </div>
                <a className="br-item" href="https://www.gov.br/pt-br/orgaos-do-governo">
                  Órgãos do Governo
                </a>
                <a className="br-item" href="https://www.gov.br/acessoainformacao/pt-br">
                  Acesso à Informação
                </a>
                <a className="br-item" href="https://www4.planalto.gov.br/legislacao">
                  Legislação
                </a>
                <a className="br-item" href="https://www.gov.br/governodigital/pt-br/acessibilidade-e-usuario/acessibilidade-digital">
                  Acessibilidade
                </a>
              </div>
            </div>
            <span className="br-divider vertical mx-half mx-sm-1"></span>
            <div className="header-functions dropdown">
              <button
                className="br-button circle small"
                type="button"
                data-toggle="dropdown"
                aria-label="Abrir Funcionalidades do Sistema"
              >
                <i className="fas fa-th" aria-hidden="true"></i>
              </button>
              <div className="br-list">
                <div className="header">
                  <div className="title">Funcionalidades do Sistema</div>
                </div>
                <div className="br-item">
                  <button
                    className="br-button circle small"
                    type="button"
                    aria-label="Funcionalidade 4"
                  >
                    <i className="fas fa-adjust" aria-hidden="true"></i>
                    <span className="text">Funcionalidade 4</span>
                  </button>
                </div>
              </div>
            </div>
            <div className="header-search-trigger">
              <button
                className="br-button circle"
                type="button"
                aria-label="Abrir Busca"
                data-toggle="search"
                data-target=".header-search"
              >
                <i className="fas fa-search" aria-hidden="true"></i>
              </button>
            </div>
          </div>
        </div>
        <div className="header-bottom">
          <div className="header-menu">
            <div className="header-menu-trigger">
              <button
                className="br-button small circle"
                type="button"
                aria-label="Menu"
                onClick={toggleMenu}
              >
                <i className="fas fa-bars" aria-hidden="true"></i>
              </button>
            </div>
            <div className="header-info"> 
              <Link className="header-title" to='/'>Gestão Contribuição</Link>
            </div>
          </div>
        </div>
        {isMenuVisible && (
          <div className="dropdown-menu">
            <ButtonMenu />
          </div>
        )}
      </div>
    </header>
  );
};

export default Header;
