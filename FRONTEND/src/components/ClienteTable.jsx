import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Button } from 'react-bootstrap';
import ClienteForm from './ClienteForm';

const ClienteTable = () => {
  const [clientes, setClientes] = useState([]);
  const [clienteEditando, setClienteEditando] = useState(null);

  const obtenerClientes = async () => {
    try {
      const res = await axios.get("http://localhost:8080/api/clientes");
      setClientes(res.data);
    } catch (err) {
      alert("Error al obtener clientes");
    }
  };

  useEffect(() => {
    obtenerClientes();
  }, []);

  const eliminarCliente = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/clientes/${id}`);
      obtenerClientes();
    } catch (err) {
      alert("Error al eliminar cliente");
    }
  };

  return (
    <div className="container mt-4">
      <h2>Clientes consumo</h2>

      <Button className="mb-3" variant="success" data-bs-toggle="modal" data-bs-target="#modalAgregarCliente">
        Agregar cliente
      </Button>

      {/* modal Agregar */}
      <ClienteForm modalId="modalAgregarCliente" onSuccess={obtenerClientes}/>

      {/* Modal Editar */}
      <ClienteForm
        modalId="modalEditarCliente"
        cliente={clienteEditando}
        onSuccess={() => {
          setClienteEditando(null);
          obtenerClientes();
        }}
      />

      <div className="d-flex justify-content-center">
        <div>
          <Table striped bordered hover responsive className="text-center">
            <thead>
              <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>CURP</th>
                <th>Fecha Nacimiento</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              {clientes.map((cliente) => (
                <tr key={cliente.id}>
                  <td>{cliente.id}</td>
                  <td>{cliente.nombre}</td>
                  <td>{cliente.apellidos}</td>
                  <td>{cliente.curp}</td>
                  <td>{cliente.fechaNacimiento}</td>
                  <td>
                    <Button
                      size="sm"
                      variant="primary"
                      data-bs-toggle="modal"
                      data-bs-target="#modalEditarCliente"
                      onClick={() => setClienteEditando(cliente)}
                    >
                      Editar
                    </Button>{' '}
                    <Button
                      size="sm"
                      variant="danger"
                      onClick={() => eliminarCliente(cliente.id)}
                    >
                      Eliminar
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    </div>
  );
};

export default ClienteTable;
