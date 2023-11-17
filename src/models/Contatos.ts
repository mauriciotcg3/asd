import Telefone from "./Telefone";

export default interface Contatos {
  id: number;
  nome: string;
  idade: number;
  telefones?: Telefone[];
}