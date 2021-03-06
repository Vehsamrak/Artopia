package artopia.service.command;

import artopia.command.AuthorsCommand;
import artopia.command.CloseCommand;
import artopia.command.DownCommand;
import artopia.command.EastCommand;
import artopia.command.HelpCommand;
import artopia.command.LookCommand;
import artopia.command.NorthCommand;
import artopia.command.OpenCommand;
import artopia.command.QuitCommand;
import artopia.command.SouthCommand;
import artopia.command.UpCommand;
import artopia.command.WestCommand;
import artopia.command.infrastructure.AbstractCommand;
import artopia.service.locator.AbstractService;
import artopia.service.locator.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Vehsamrak
 */
public class CommandRepository extends AbstractService
{
    final private Map<AbstractCommand, String[]> commandList = new LinkedHashMap<>();

    /**
     * Список всех игровых команд
     * TODO: 01.01.16 Должно быть вынесено в отдельный конфиг
     */
    public CommandRepository()
    {
        this.commandList.put(new NorthCommand(), new String[]{"north", "север"});
        this.commandList.put(new EastCommand(), new String[]{"east", "восток"});
        this.commandList.put(new SouthCommand(), new String[]{"south", "юг"});
        this.commandList.put(new WestCommand(), new String[]{"west", "запад"});
        this.commandList.put(new UpCommand(), new String[]{"up", "вверх"});
        this.commandList.put(new DownCommand(), new String[]{"down", "вниз", "низ"});
        this.commandList.put(new HelpCommand(), new String[]{"help", "помощь", "справка", "?"});
        this.commandList.put(new QuitCommand(), new String[]{"quit", "конец", "выход"});
        this.commandList.put(new AuthorsCommand(), new String[]{"authors", "credits", "авторы"});
        this.commandList.put(new LookCommand(), new String[]{"look", "смотреть"});
        this.commandList.put(new OpenCommand(), new String[]{"open", "открыть"});
        this.commandList.put(new CloseCommand(), new String[]{"close", "закрыть"});
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
     * @param firstCommand  Первая команда и ее алиасы
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

    @Override
    public Service getName()
    {
        return Service.COMMAND_REPOSITORY;
    }
}
