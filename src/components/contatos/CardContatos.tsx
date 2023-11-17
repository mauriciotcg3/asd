import { AiFillEdit, AiFillDelete } from "react-icons/ai"
import Contatos from "../../models/Contatos"
import Telefone from "../../models/Telefone";
import { buscar, deletar } from "../../service/Service";
import { useEffect, useState } from "react";
import { buscarTelefonesPorContato } from "../../service/ServiceTelefone";

interface ContatosProps {
    post: Contatos;
}

export function CardContatos({ post }: ContatosProps) {
    const deleteContato = async (id: number) => {
      await deletar(id);
    };

    const [telefones, setTelefones] = useState<Telefone[]>([]);

        useEffect(() => {
            const carregarTelefones = async () => {
                try {
                    const telefonesDoContato = await buscarTelefonesPorContato(post.id);
                    console.log("Telefones do Contato", telefonesDoContato);
                    setTelefones(telefonesDoContato || []);
                } catch (error) {
                console.error("Erro ao buscar telefones:", error);
            }
        };

  carregarTelefones();
}, [post.id]);


    return (
      <div className='flex justify-center flex-col mt-5 text-lg'>
        <div className=''>
          <div className='flex bg-gray-800 justify-between p-4 rounded-lg items-center shadow-lg'>
            <div className="flex w-1/12">Nome: {post.nome}</div>
            <div className="flex w-1/12">Idade: {post.idade}</div>
            <div>
              Telefone: {telefones &&
                telefones.map((telefone: Telefone, index: number) => (
                  <span key={index}>{telefone.numero}</span>
                ))}
            </div>
            <div className='acoes'>
              <button className='bg-slate-700 mx-1 p-2 rounded hover:bg-blue-500'><AiFillEdit/></button>
              <button className='bg-slate-700 mx-1 p-2 rounded hover:bg-red-500'
                onClick={() => deleteContato(post.id)}><AiFillDelete/></button>
            </div>
          </div>
        </div>
      </div>
    );
  }