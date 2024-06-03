import Govlogonegativa from "../assets/images/govbrNegativa.svg";
import { Link } from "react-router-dom";

const Footer = () => {
  return (
    <footer className="br-footer">
      <div className="container-lg">
        <div className="logo">
          <img src={Govlogonegativa} alt="Imagem" />
        </div>
        <div className="br-list horizontal" data-toggle="data-toggle" data-sub="data-sub">
          {[
            {
              categoria: "Navegação",
              links: [
                { label: "Home", path: "/" },
                { label: "Listar Alíquotas", path: "/aliquotas" },
                { label: "Criar Alíquotas", path: "/aliquotas/criar" },
                { label: "Deletar Alíquotas", path: "/aliquotas/deletar" },
                { label: "Consultar Contribuintes", path: "/contribuintes/consultar" },
              ],
            },
            {
              categoria: "Serviços",
              links: [
                { label: "Gestão de Contribuintes", path: "/gestao-contribuintes" },
                { label: "Gestão de Benefícios", path: "/gestao-beneficios" },
                { label: "Gestão de Empréstimos", path: "/gestao-emprestimos" },
              ],
            },
          ].map((item, index) => (
            <div className="col-2" key={index}>
              <div className="br-item header" href="javascript:void(0)">
                <div className="content text-down-01 text-bold text-uppercase">
                  {item.categoria}
                </div>
                <div className="support">
                  <i className="fas fa-angle-down" aria-hidden="true"></i>
                </div>
              </div>
              <div className="br-list">
                <span className="br-divider d-md-none"></span>
                {item.links.map((link, linkIndex) => (
                  <div className="br-item" key={linkIndex}>
                    <Link className="content" to={link.path}>
                      {link.label}
                    </Link>
                  </div>
                ))}
                <span className="br-divider d-md-none"></span>
              </div>
            </div>
          ))}
        </div>
        <div className="d-none d-sm-block">
          <div className="row align-items-end justify-content-between py-5">
            <div className="col">
              <div className="social-network">
                <div className="social-network-title">Redes Sociais</div>
                <div className="d-flex">
                  <a
                    className="br-button circle"
                    href="https://www.facebook.com/governodobrasil"
                    aria-label="Compartilhar por Facebook"
                  >
                    <i className="fab fa-facebook-f" aria-hidden="true"></i>
                  </a>
                  <a
                    className="br-button circle"
                    href="https://x.com/govbr"
                    aria-label="Compartilhar por Twitter"
                  >
                    <i className="fab fa-twitter" aria-hidden="true"></i>
                  </a>
                  <a
                    className="br-button circle"
                    href="https://www.linkedin.com/company/governo-do-brasil/"
                    aria-label="Compartilhar por Linkedin"
                  >
                    <i className="fab fa-linkedin-in" aria-hidden="true"></i>
                  </a>
                  <a
                    className="br-button circle"
                    href="https://www.whatsapp.com/channel/0029Va2zbqm7dmeR3lddrp38"
                    aria-label="Compartilhar por Whatsapp"
                  >
                    <i className="fab fa-whatsapp" aria-hidden="true"></i>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <span className="br-divider my-3"></span>
      <div className="container-lg">
        <div className="info">
          <div className="text-down-01 text-medium pb-3">
            Texto destinado a exibição de informações relacionadas à&nbsp;<strong>licença de uso.</strong>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
