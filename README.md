<h1 align="center">🦺 Player Data Backup Tool</h1>
<h5 align="center">使用Java基于MongoDB开发的Minecraft服务器玩家背包备份工具</h5>

------

因服内经常有需要对玩家背包进行回档的事务，为简化管理，开发了此工具

完成了所有的功能，适用于生存服，群组可考虑多开

**注意事项：**
1. 工具依赖 `MongoDB` ，使用前请填写 `地址` 、 `数据库` 、 `集合` 。
2. 使用前请配置玩家数据目录（以 `\` 结尾），正版记得 `重新解析UUID映射表`  ，盗版自行按照格式填写 `uuid2name.json`
3. 配置文件放在 `jar包` 内，可在构建前在 `resources` 内填写，也可以构建后在jar包内填写
4. 工具使用前请先确认 `MongoDB` 已经启动，否则无法连接
