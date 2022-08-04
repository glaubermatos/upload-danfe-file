import { InvoiceProvider } from './hooks/useInvoices';
import { Home } from './view/home';

function App() {

  return (
    <InvoiceProvider>
      <Home />
    </InvoiceProvider>
  );
}

export default App;
