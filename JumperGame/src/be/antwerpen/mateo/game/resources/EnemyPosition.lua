math.randomseed(os.time())

function EnemyPositionGenerate(list)
    local rest = list%5
    if rest == 0 then
        rest = -1
    end
    return rest == -1
end