import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Form, Button } from 'react-bootstrap';

const ClienteForm = ({ cliente, onSuccess, modalId }) => {
  const [formData, setFormData] = useState({
    nombre: '', apellidos: '', curp: '', fechaNacimiento: ''
  });

  useEffect(() => {
    if (cliente) {
      setFormData(cliente);
    } else {
      setFormData({
        nombre: '', apellidos: '', curp: '', fechaNacimiento: ''
      });
    }
  }, [cliente]);

  useEffect(() => {
    const modalElement = document.getElementById(modalId);
    
    const handleHidden = () => {
      if (!cliente) {
        setFormData({ nombre: '', apellidos: '', curp: '', fechaNacimiento: '' });
      }
    };

    modalElement?.addEventListener('hidden.bs.modal', handleHidden);
    
    return () => {
      modalElement?.removeEventListener('hidden.bs.modal', handleHidden);
    };
  }, [modalId, cliente]);

  const actualizarCampo = e => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const guardarCliente = async e => {
    e.preventDefault();
    try {
      if (cliente) {
        await axios.put(`http://localhost:8080/api/clientes/${cliente.id}`, formData);
      } else {
        await axios.post("http://localhost:8080/api/clientes", formData);
      }
      onSuccess();
      document.getElementById(`cerrar-${modalId}`).click();
    } catch (err) {
      alert("Error al guardar cliente");
    }
  };

  return (
    <div className="modal fade" id={modalId} tabIndex="-1" aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <Form onSubmit={guardarCliente}>
            <div className="modal-header">
              <h5 className="modal-title">
                {cliente ? 'Editar cliente' : 'Agregar cliente'}
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
                id={`cerrar-${modalId}`}
              ></button>
            </div>

            <div className="modal-body">
              <Form.Group className="mb-3">
                <Form.Label>Nombre</Form.Label>
                <Form.Control
                  type="text"
                  name="nombre"
                  value={formData.nombre}
                  onChange={actualizarCampo}
                  required
                />
              </Form.Group>

               <Form.Group className="mb-3">
                <Form.Label>Apellidos</Form.Label>
                <Form.Control
                  type="text"
                  name="apellidos"
                  value={formData.apellidos}
                  onChange={actualizarCampo}
                  required
                />
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>CURP</Form.Label>
                <Form.Control
                  type="text"
                  name="curp"
                  value={formData.curp}
                  onChange={actualizarCampo}
                  required
                />
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>Fecha de nacimiento</Form.Label>
                <Form.Control
                  type="date"
                  name="fechaNacimiento"
                  value={formData.fechaNacimiento}
                  onChange={actualizarCampo}
                  required
                />
              </Form.Group>
            </div>

            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Cancelar
              </button>
              <Button variant="primary" type="submit">
                Guardar
              </Button>
            </div>
          </Form>
        </div>
      </div>
    </div>
  );
};

export default ClienteForm;