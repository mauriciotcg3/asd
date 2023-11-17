import axios from "axios";

const baseURL = "http://localhost:8080/telefone";

export const buscarTelefonesPorContato = async (contatoId: number) => {
  const resposta = await axios.get(`${baseURL}/${contatoId}`);
  return resposta.data;
};

export const cadastrarTelefone = async (contatoId: number, dados: Object) => {
  try {
    const resposta = await axios.post(`${baseURL}/${contatoId}`, dados);
    return resposta.data;
  } catch (error) {
    throw error;
  }
};

export const atualizarTelefone = async (
  url: string,
  dados: Object,
  setDados: Function,
  header: Object
) => {
  const resposta = await axios.put(url, dados, header);
  setDados(resposta.data);
};

export const deletarTelefone = async (id: number) => {
  await axios.delete(`${baseURL}/${id}`);
};