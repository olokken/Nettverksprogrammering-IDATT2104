import { useState } from 'react';

function App() {

  const [code, setCode] = useState("");
  const [result, setResult] = useState("");

  const onCodeChange = (event) => {
    setCode(event.target.value);
  }

  const onExecute = async () => {
    const response = await fetch('http://localhost:5000/horeri', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ code: code })
    });
    const { result } = await response.json();
    setResult(result)
  }

  return (
    <div className="container">

      
      <div className="form-group mb-3">
        <label htmlFor="code">Skriv inn python-code under:</label>
        <textarea className="form-control" id="code" name="code" rows="8" onChange={onCodeChange}></textarea>
      </div>

      <button type="button" onClick={onExecute} className="btn btn-primary">Execute</button>

      <hr />

      <div id="output">
        {result}
      </div>

    </div>
  );
}

export default App;
