import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Page from "../component/Page";
import Form from "../component/Form";

const SolarWatch = () => {
  const token = JSON.parse(localStorage.getItem("jwt"));
  const [date, setDate] = useState("");
  const [city, setCity] = useState("");
  const [sunrise, setSunrise] = useState("");
  const [sunset, setSunset] = useState("");
  const [error, setError] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    if (!token) {
      navigate("/");
    }
  }, [navigate]);

  async function handleSubmit(e) {
    e.preventDefault();
    setError("");

    try {
      const response = await fetch(`/api/sun-times/${city}/${date}`, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      if (response.status === 401 || response.status === 403) {
        localStorage.removeItem("jwt");
        navigate("/");
        console.error("Authentication failed");
      } else if (!response.ok) {
        setError("Wrong city, please try another one.");
      } else {
        const data = await response.json();
        setSunrise(data.sunrise);
        setSunset(data.sunset);
      }
    } catch (err) {
      setError("An error occurred. Please try again later.");
    }
  }

  return (
    <Page showLogout={true} contentClass="solar-content">
      <Form
        title="Solar Watch"
        fields={[
          {
            label: "Date:",
            type: "date",
            id: "date",
            value: date,
            onChange: (e) => setDate(e.target.value),
          },
          {
            label: "City:",
            type: "text",
            id: "city",
            value: city,
            onChange: (e) => setCity(e.target.value),
            placeholder: "London",
          },
        ]}
        onSubmit={handleSubmit}
        buttonText="Get Sunrise & Sunset"
        errorMessage={error}
      />
      {sunrise && sunset && (
        <div className="results container">
          <p>
            Sunrise: <span>{sunrise}</span>
          </p>
          <p>
            Sunset: <span>{sunset}</span>
          </p>
        </div>
      )}
    </Page>
  );
};

export default SolarWatch;
