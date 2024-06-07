import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Page from "../component/Page";
import Form from "../component/Form";

const Registration = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  async function handleRegistration(e) {
    e.preventDefault();
    await registerUser();
    navigate(`/`);
  }

  async function registerUser() {
    await fetch("/api/member/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username, password }),
    });
  }

  return (
    <Page contentClass="reg-content">
      <Form
        title="Registration"
        fields={[
          { label: "Username:", type: "text", id: "username", value: username, onChange: (e) => setUsername(e.target.value) },
          { label: "Password:", type: "password", id: "password", value: password, onChange: (e) => setPassword(e.target.value) },
        ]}
        onSubmit={handleRegistration}
        buttonText="Register"
      />
    </Page>
  );
};

export default Registration;
