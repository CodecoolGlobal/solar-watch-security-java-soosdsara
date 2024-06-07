import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Page from "../component/Page";
import Form from "../component/Form";

const Login = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  async function handleLogin(e) {
    e.preventDefault();
    const response = await loginUser();

    if (!response.ok) {
      localStorage.removeItem("jwt");
      setError("Incorrect username or password");
      return;
    }

    const responseBody = await response.json();
    localStorage.setItem("jwt", JSON.stringify(responseBody.jwt));
    navigate(`/solar-watch`);
  }

  async function loginUser() {
    const response = await fetch("/api/member/signin", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username, password }),
    });
    return response;
  }

  return (
    <Page contentClass="login-content">
      <Form
        title="Login"
        fields={[
          { label: "Username:", type: "text", id: "username", value: username, onChange: (e) => setUsername(e.target.value) },
          { label: "Password:", type: "password", id: "password", value: password, onChange: (e) => setPassword(e.target.value) },
        ]}
        onSubmit={handleLogin}
        buttonText="Login"
        errorMessage={error}
        additionalButton={
          <button className="register-button" onClick={() => navigate("/registration")}>
            Register
          </button>
        }
      />
    </Page>
  );
};

export default Login;
