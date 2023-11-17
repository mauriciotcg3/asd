import axios from "axios";

const baseURL = "http://localhost:8080/contato";

export const buscar = async() => {
  const resposta = await axios.get(`${baseURL}`)
  const data = resposta.data
  console.log(data)
  return data;
}

export const cadastrar = async(dados: Object) => {
  try {
    const resposta = await axios.post(`${baseURL}`, dados)
  } catch (error) {
    throw error
  }
}

export const atualizar = async(url: string, dados: Object, setDados: Function, header: Object) => {
  const resposta = await axios.put(url, dados, header)
  setDados(resposta.data)
}

export const deletar = async(id: number) => {
  await axios.delete(`${baseURL}/${id}`)
}