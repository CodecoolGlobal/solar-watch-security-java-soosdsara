
const Form = ({ title, fields, onSubmit, buttonText, errorMessage, additionalButton }) => (
  <div className="container">
    <h2>{title}</h2>
    {errorMessage && <p className="error-message">{errorMessage}</p>}
    <form onSubmit={onSubmit}>
      {fields.map(({ label, type, id, value, onChange, placeholder }) => (
        <div key={id}>
          <label htmlFor={id}>{label}</label>
          <input type={type} id={id} value={value} onChange={onChange} placeholder={placeholder}/>
        </div>
      ))}
      <button type="submit" className="submit-button">{buttonText}</button>
    </form>
    {additionalButton}
  </div>
);

export default Form;
