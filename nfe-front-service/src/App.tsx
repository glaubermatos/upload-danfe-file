import logo from './logo.svg';
import './App.css';
import { InvoiceProvider } from './hooks/useInvoices';
import { InvoiceTable } from './components/InvoiceTable';
import { InvoiceUpload } from './components/InvoiceUpload';

function App() {
  return (
    <InvoiceProvider>
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <br />
          <br />
        <InvoiceUpload />
        <InvoiceTable />
        </header>
        

      </div>

    </InvoiceProvider>
  );
}

export default App;
