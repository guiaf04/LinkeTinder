package service

import model.Empresa
import dao.EmployeeDAO
import dao.interfaces.ISampleDAO

class EmployeeService {
    ISampleDAO employeeDAO = new EmployeeDAO()

    private static String getID(String employee){
        def matcher = (employee =~ /id:(\d+)/)
        matcher.find()
        return matcher.group(1)
    }

    boolean addEmployee(Empresa empresa){
        if (employeeDAO.getElementByCNPJ(empresa.getCnpj()) != null){
            println("Essa empresa já está cadastrado, tente usar informações diferentes para cadastrar uma nova empresa")
            return false
        }

        return employeeDAO.criar(empresa)
    }

    List<String> listEmployees(){
        return employeeDAO.listar()
    }
    
    boolean editEmployee(Empresa empresa){
        String olderEmployee = employeeDAO.getElementByCNPJ(empresa.getCnpj())

        if (olderEmployee == null){
            println "Essa empresa não está cadastrada, então não é possível editá-la"
            return false
        }

        int id = getID(olderEmployee).toInteger()

        List<String> values = new ArrayList<>()
        List<String> fields = new ArrayList<>()

        // Converter a string em um mapa, ignorando o campo 'id'
        List<String> campos = olderEmployee.replaceAll(/[\[|\]]/, '').split(', ')

        Map<String, String> novoEmpresaMap = campos.collectEntries { campo ->
            def (chave, valor) = campo.split(':')
            [(chave): valor]
        }.findAll { chave, _ -> chave != 'id' } // Remover o 'id' do mapa

        // Comparar e encontrar valores alterados
        novoEmpresaMap.each { chave, valorAtual ->
            String propriedade = chave.replace('_', '') // Remover underscore para corresponder aos nomes das propriedades
            String novoValor = empresa."$propriedade"

            if (valorAtual.toString() != novoValor.toString()) {
                fields.add(chave)
                values.add(novoValor)
            }
        }

        if (fields.isEmpty()) {
            println "Nenhuma alteração foi encontrada."
            return false
        }

        println "Campos a serem atualizados: $fields"
        println "Novos valores: $values"

        return employeeDAO.atualizar(fields, values, id)
    }

    boolean deleteEmployee(Empresa empresa){
        String employee = employeeDAO.getElementByCNPJ(empresa.getCnpj())

        if (employee == null){
            println("Esse usuário não está cadastrado, tente novamente")
            return false
        }

        int id = getID(employee).toInteger()

        return employeeDAO.deletar(id)
    }
}
