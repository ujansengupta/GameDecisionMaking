from __future__ import division
import os, json, math
from pprint import pprint

def calcNetEntropy(logs, totalSize):

	entropy_net = 0
	stateCount = {}
	stateLists = {}
	stateProbs = {}

	for log in logs:
		state = log["STATE"]

		if state in stateCount:
			stateCount[state] += 1
			stateLists[state].append(log)
		else:
			stateCount[state] = 1
			stateLists[state] = [log]



	for state in stateCount.keys():
		print (state, stateCount[state])
		stateProbs[state] = stateCount[state]/totalSize
		entropy_net -= (stateProbs[state] * math.log(stateProbs[state]))

	print stateProbs
	print 'Net Entropy : ' + str(entropy_net)
	return entropy_net

def calcConditionEntropy(condition, dict, logs, totalSize, entropy_net):

	dict['True'] = {}
	dict['False'] = {}
	dict['entropy_true'] = 0
	dict['entropy_false'] = 0

	for log in logs:
		eval = str(log[condition])
		state = log["STATE"]

		if state in dict[eval]:
			dict[eval][state] += 1
		else:
			dict[eval][state] = 1

	trueSize = sum(dict['True'].values())
	falseSize = sum(dict['False'].values())


	for state in dict['True'].keys():
		prob = dict['True'][state]/trueSize
		dict['entropy_true'] -= prob * math.log(prob)

	for state in dict['False'].keys():
		prob = dict['False'][state]/falseSize
		dict['entropy_false'] -= prob * math.log(prob)

	dict['IG'] = entropy_net - ((trueSize/totalSize) * dict['entropy_true']) - ((falseSize/totalSize) * dict['entropy_false'])
	print (condition, dict)




with open(os.path.abspath('../Training_Data/test1.json')) as data_file:    
    data = json.load(data_file)

logs = data["Logs"]
totalSize = len(logs)

boundary = {}
obstacle = {}
distance = {}
los = {}

# entropy_net = calcNetEntropy(logs, totalSize)

# calcConditionEntropy("BOUNDARY CHECK", boundary, logs, totalSize, entropy_net)
# calcConditionEntropy("OBSTACLE CHECK", obstacle, logs, totalSize, entropy_net)
# calcConditionEntropy("DISTANCE CHECK", distance, logs, totalSize, entropy_net)
# calcConditionEntropy("LOS CHECK", los, logs, totalSize, entropy_net)

# LOS_T_logs = []
# LOS_F_logs = []

# for log in logs:
# 	if str(log["LOS CHECK"]) == "True":
# 		LOS_T_logs.append(log)
# 	else:
# 		LOS_F_logs.append(log)

# totalSize = len(LOS_F_logs)

# entropy_net = calcNetEntropy(LOS_F_logs, totalSize)
# calcConditionEntropy("BOUNDARY CHECK", boundary, LOS_F_logs, totalSize, entropy_net)
# calcConditionEntropy("OBSTACLE CHECK", obstacle, LOS_F_logs, totalSize, entropy_net)
# calcConditionEntropy("DISTANCE CHECK", distance, LOS_F_logs, totalSize, entropy_net)

# OBS_T_logs = []
# OBS_F_logs = []

# # LOS_F_OBS_T_logs = []
# # LOS_F_OBS_F_logs = []

# for log in LOS_F_logs:
# 	if str(log["OBSTACLE CHECK"]) == "True":
# 		OBS_T_logs.append(log)
# 	else:
# 		OBS_F_logs.append(log)


# totalSize = len(OBS_F_logs)

# entropy_net = calcNetEntropy(OBS_F_logs, totalSize)
# calcConditionEntropy("BOUNDARY CHECK", boundary, OBS_F_logs, totalSize, entropy_net)
# calcConditionEntropy("DISTANCE CHECK", distance, OBS_F_logs, totalSize, entropy_net)






















