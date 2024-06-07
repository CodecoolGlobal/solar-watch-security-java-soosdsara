import { useNavigate } from "react-router-dom";

const Header = ({ showLogout }) => {
  const navigate = useNavigate();

  function handleLogout() {
    localStorage.removeItem("jwt");
    navigate('/');
  }

  return (
    <header className="header">
      <h1>Sunset Horizon</h1>
      {showLogout && <button className="logout-button" onClick={handleLogout}>Logout</button>}
    </header>
  );
};

export default Header;
