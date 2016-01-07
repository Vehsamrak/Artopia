package artopia.service.command;

import artopia.command.AuthorsCommand;
import artopia.command.ExitCommand;
import artopia.command.HelpCommand;
import artopia.command.LookCommand;
import artopia.command.infrastructure.AbstractCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Vehsamrak
 */
public class CommandRepository
{
    final private HashMap<AbstractCommand, String[]> commandList = new HashMap<>();

    /**
     * Список всех игровых команд
     * TODO: 01.01.16 Должно быть вынесено в отдельный конфиг
     */
    public CommandRepository()
    {
        this.commandList.put(new AuthorsCommand(), new String[]{"authors", "credits", "авторы"});
        this.commandList.put(new ExitCommand(), new String[]{"exit", "quit", "выход"});
        this.commandList.put(new LookCommand(), new String[]{"look", "смотреть"});
        this.commandList.put(new HelpCommand(), new String[]{"help", "помощь", "справка", "?"});
    }

    /**
     * Поиск по названию или алиасу команды
     * @param inputCommand Название команды или алиас
     * @return Объект команды
     */
    public AbstractCommand findByName(String inputCommand)
    {
        inputCommand = inputCommand.toLowerCase();

        for (Map.Entry<AbstractCommand, String[]> entry : this.commandList.entrySet()) {
            AbstractCommand command = entry.getKey();
            String[] commandAliases = entry.getValue();

            for (String commandAlias : commandAliases) {
                if (commandAlias.startsWith(inputCommand)) {
                    return command;
                }
            }
        }

        return null;
    }

    /**
     * @return Таблица со списком всех команд и их описаний
     */
    public String getDescriptions()
    {
        StringBuilder descriptions = new StringBuilder();
        descriptions.append("Игровые команды\n===============\n");

        for (Map.Entry<AbstractCommand, String[]> entry : this.createSortedCommandList()) {
            AbstractCommand command = entry.getKey();
            String[] commandAliases = entry.getValue();

            descriptions.append(String.join(", ", commandAliases));
            descriptions.append(" - ");
            descriptions.append(command.getDescription());
            descriptions.append("\n");
        }

        return descriptions.toString();
    }

    /**
     * @return Список отсортированных по первой букве в алфавитном порядке команд
     */
    private List<Map.Entry<AbstractCommand, String[]>> createSortedCommandList()
    {
        return this.commandList
                .entrySet()
                .stream()
                .sorted(this::sort)
                .collect(Collectors.toList());
    }

    /**
     * Метод сравнения для списка команд. Сравнение по первой букве первого алиаса
     * @param firstCommand Первая команда и ее алиасы
     * @param secondCommand Команда и алиасы для сравнения
     * @return Соотношение в числовом виде
     */
    private int sort(
            Map.Entry<AbstractCommand, String[]> firstCommand,
            Map.Entry<AbstractCommand, String[]> secondCommand
    )
    {
        String[] firstAliasArray = firstCommand.getValue();
        String[] secondAliasArray = secondCommand.getValue();

        return firstAliasArray[0].compareToIgnoreCase(secondAliasArray[0]);
    }
}