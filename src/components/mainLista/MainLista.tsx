import { useState, useEffect } from "react";
import { CardContatos } from "../contatos/CardContatos";
import Contatos from "../../models/Contatos";
import { buscar, cadastrar } from "../../service/Service";
import FormularioContatos from "../forms/fomulario";

export function MainLista() {
  const [contatos, setContatos] = useState<Contatos[]>([] as Contatos[]);
  const [controller, setController] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");

  const buscarDados = async () => {
    const data = await buscar();
    setContatos(data);
  };

  const adicionarContato = () => {
    setController(true);
  };

  useEffect(() => {
    buscarDados();
  }, [controller]);

  const filteredContatos = contatos.filter((contato) =>
    contato.nome.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <>
      {controller ? (
        <FormularioContatos
          onSubmit={(contato: Contatos): void => {}}
          close={(): void => {
            setController(false);
          }}
        />
      ) : (
        <div className="flex h-screen bg-gray-800 justify-center items-center">
          <div className="bg-slate-900 p-20 w-10/12 rounded-xl text-neutral-50">
            <div className="font-display text-4xl font-bold text-center">
              Lista de Contatos
            </div>
            <div className="flex items-center justify-between mb-4">
              <button
                className="bg-gray-400 rounded-lg p-2"
                onClick={() => adicionarContato()}
              >
                Adicionar Contato
              </button>
              <input
                type="text"
                placeholder="Pesquisar..."
                className="p-2 border rounded text-black"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
            </div>
            <div className="bg-slate-800 rounded p-4" style={{ maxHeight: "400px", overflowY: "auto" }}>
              {filteredContatos.map((contato) => (
                <CardContatos key={contato.id} post={contato} />
              ))}
            </div>
          </div>
        </div>
      )}
    </>
  );
}
