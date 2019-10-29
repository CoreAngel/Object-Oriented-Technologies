package filesystem.console.commands;

import filesystem.console.Context;

public class AbstractCommand {

    private Context context;

    public AbstractCommand(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

}
