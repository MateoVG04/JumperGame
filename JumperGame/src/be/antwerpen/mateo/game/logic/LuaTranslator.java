package be.antwerpen.mateo.game.logic;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.util.List;

/**
 * Class that makes it easier to communicate with lua scripts,
 * translation of variables happens in this class.
 */
public class LuaTranslator {
    private Globals globals;
    private LuaValue script;

    public LuaTranslator(String scriptPath) {
        globals = JsePlatform.standardGlobals();
        script = globals.loadfile(scriptPath);
        script.call();
    }

    public boolean EnemyPosition(int aantalPlatformen) {
        LuaValue EnemyPositionGenerate = globals.get("EnemyPositionGenerate");
        LuaValue result = EnemyPositionGenerate.call(LuaValue.valueOf(aantalPlatformen));
        return result.toboolean();
    }
}
